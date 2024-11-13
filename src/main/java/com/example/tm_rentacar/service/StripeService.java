package com.example.tm_rentacar.service;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.tm_rentacar.dto.ReservationDto;
import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.repository.CarRepository;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.PermissionException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.Mode;
import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StripeService {
	//定数
	private static final PaymentMethodType PAYMENT_METHOD_TYPE = SessionCreateParams.PaymentMethodType.CARD;//決済方法
	private static final String CURRENCY = "jpy";// 通過
	private static final long QUANTITY = 1L;//数量
	private static final Mode MODE = SessionCreateParams.Mode.PAYMENT;//支払いモード
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //日付のフォーマット
	
	//Stripeのシークレットキー
	@Value("${stripe.api-key}")
	private String stripeApiKey;
	// 決済成功時のリダイレクト先URL
	@Value("${stripe.success-url}")
	private String stripeSuccessUrl;
	// 決済キャンセル時のリダイレクト先URL
	@Value("${stripe.cancel-url}")
	private String stripeCancelUrl;
	
	private final CarRepository carRepository;
	private final ReservationService reservationService;
	
	public StripeService(CarRepository carRepository, ReservationService reservationService) {
		this.carRepository = carRepository;
		this.reservationService = reservationService;
	}
	
	//依存性の注入後に一度だけ実行するメソッド
	@PostConstruct
	private void init() {
		//Stripeのシークレットキーを設定
		Stripe.apiKey = stripeApiKey;
	}
	
	public String createStripeSession(ReservationDto reservationDto, User user) {
		Optional<Car> optionalCar = carRepository.findById(reservationDto.getCarId());
		Car car = optionalCar.orElseThrow(() -> new EntityNotFoundException("指定されたIDの車両が存在しません。"));
		
		//車種
		String carName = car.getModel();
		//料金
		long unitAmount = (long) reservationDto.getAmount();
		//メタデータ（付随情報）
		String carId = reservationDto.getCarId().toString();
		String userId = user.getId().toString();
		String startDate = reservationDto.getStartDate().format(DATE_TIME_FORMATTER);
		String endDate = reservationDto.getEndDate().format(DATE_TIME_FORMATTER);
		String amount = reservationDto.getAmount().toString();
		
		
		//セッションに入れる支払い情報
		SessionCreateParams sessionCreateParams =
			SessionCreateParams.builder()
				.addPaymentMethodType(PAYMENT_METHOD_TYPE)
				.addLineItem(
					SessionCreateParams.LineItem.builder()
						.setPriceData(
							SessionCreateParams.LineItem.PriceData.builder()
								.setProductData(
									SessionCreateParams.LineItem.PriceData.ProductData.builder()	
										.setName(carName)
										.build())
									.setUnitAmount(unitAmount)
									.setCurrency(CURRENCY)
									.build())
								.setQuantity(QUANTITY)
								.build())
							.setMode(MODE)
							.setSuccessUrl(stripeSuccessUrl)
							.setCancelUrl(stripeCancelUrl)
							.setPaymentIntentData(
									SessionCreateParams.PaymentIntentData.builder()
										.putMetadata("carId", carId)
										.putMetadata("userId", userId)
										.putMetadata("startDate", startDate)
										.putMetadata("endDate",endDate)
										.putMetadata("amount", amount)
										.build())
									.build();
		
			try {
				//Stripeに送信する支払い情報をセッションとして作成
				Session session = Session.create(sessionCreateParams);
				
				return session.getId();
			}catch(RateLimitException e) {
				System.out.println("短時間のうちに過剰な回数のAPIコールが行われました。");
				return "";
			}catch(InvalidRequestException e) {
				System.out.println("APIコールのパラメーターが誤っているか、状態が誤っているか、方法が無効でした。");
	             return "";
			}catch(PermissionException e) {
				System.out.println("このリクエストに使用されたAPIキーには必要な権限がありません。");
	             return "";
			}catch(AuthenticationException e) {
				System.out.println("Stripeは、提供された情報では認証できません。");
	             return "";
			}catch(ApiConnectionException e) {
				System.out.println("お客様のサーバーとStripeの間でネットワークの問題が発生しました。");
	             return "";
			}catch(ApiException e) {
				System.out.println("Stripe側で問題が発生しました（稀な状況です）。");
	             return "";
			}catch(StripeException e) {
				System.out.println("Stripeとの通信中に予期せぬエラーが発生しました。");
	             return "";
			}
			//StripeExceptionは検査例外→例外処理は必須
	}
	
	//Sessionから予約情報を取得してReservationServiceクラスを介してDBに登録
	public void processSessionCompleted(Event event) {
		//EventオブジェクトからStripeオブジェクトを取得する
		//Event->Stripeから通知されるイベントの内容を表現したオブジェクト
		//StripeObject->StripeのAPIから返されるデータを表現する基本的なオブジェクト
		Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject().map(Session.class::cast);
		

		System.out.println(optionalStripeObject);

		//stripeObjectが存在する場合と、存在しない場合の処理
		optionalStripeObject.ifPresentOrElse(stripeObject -> {
			// StripeObjectオブジェクトを型変換
			Session session = (Session)stripeObject;
			//"payment_intent"情報を展開する（詳細情報を含める）ように指定したSessionRetrieveParamsオブジェクトを生成する
			SessionRetrieveParams sessionRetrieveParams = SessionRetrieveParams.builder().addExpand("payment_intent").build();
			
			try {
				//支払い情報を含む詳細なセッション情報を取得する
				session = Session.retrieve(session.getId(), sessionRetrieveParams, null);
					System.out.println(session.getId());
				//詳細なセッション情報からメタデータを取り出す。
				Map<String, String> sessionMetadata = session.getPaymentIntentObject().getMetadata();
				//予約情報をDBに登録する
					System.out.println(sessionMetadata);
				reservationService.createReservation(sessionMetadata);
				
				System.out.println("予約情報の登録処理が成功しました。");
				
			}catch(RateLimitException e) {
				System.out.println("短時間のうちに過剰な回数のAPIコールが行われました。");
			}catch(InvalidRequestException e) {
				System.out.println("APIコールのパラメーターが誤っているか、状態が誤っているか、方法が無効でした。");
			}catch(PermissionException e) {
				System.out.println("このリクエストに使用されたAPIキーには必要な権限がありません。");
			}catch(AuthenticationException e) {
				System.out.println("Stripeは、提供された情報では認証できません。");
			}catch(ApiConnectionException e) {
				System.out.println("お客様のサーバーとStripeの間でネットワークの問題が発生しました。");
			}catch(ApiException e) {
				System.out.println("Stripe側で問題が発生しました（稀な状況です）。");
			}catch(StripeException e) {
				System.out.println("Stripeとの通信中に予期せぬエラーが発生しました。");
			}catch(Exception e) {
				System.out.println("予約情報の登録処理中に予期せぬエラーが発生しました。");
				System.out.println(e);
			}
		},
		() -> {
			System.out.println("予約情報の登録処理が失敗しました。");
		});
		// StripeのAPIとstripe-javaライブラリのバージョンをコンソールに出力する
        System.out.println("Stripe API Version: " + event.getApiVersion());
        System.out.println("stripe-java Version: " + Stripe.VERSION + ", stripe-java API Version: " + Stripe.API_VERSION);
	}
}

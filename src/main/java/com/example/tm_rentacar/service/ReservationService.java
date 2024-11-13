package com.example.tm_rentacar.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.entity.Reservation;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.enums.ReservationStatus;
import com.example.tm_rentacar.repository.CarRepository;
import com.example.tm_rentacar.repository.ReservationRepository;
import com.example.tm_rentacar.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final CarRepository carRepository;
	private final UserRepository userRepository;
	
	public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, UserRepository userRepository) {
		this.reservationRepository = reservationRepository;
		this.carRepository = carRepository;
		this.userRepository = userRepository;
	}
	
	//ユーザー情報に紐づく予約を新しい順で取得（ページネーションあり）
	public Page<Reservation> findReservationByUserOrderByCreatedAtDesc(User user, Pageable pageable){
		return reservationRepository.findByUserOrderByCreatedAtDesc(user, pageable);
	}
	
	//利用開始日時が利用終了日時より前かチェック
	public boolean isStartBeforeEnd(LocalDateTime startDate, LocalDateTime endDate) {
		return startDate.isBefore(endDate);
	}
	
	//料金を計算する
	public Integer caluculateAmount(LocalDateTime startDate, LocalDateTime endDate, BigDecimal rentalRate) {
		long rentalDuration = ChronoUnit.HOURS.between(startDate, endDate);
		BigDecimal amount = rentalRate.multiply(BigDecimal.valueOf(rentalDuration)); 
		
		return amount.intValue();
	}
	
	//予約のレコード数を取得する
	public long countReservations() {
		return reservationRepository.count();
	}
	
	//idが最も大きい予約を取得する
	public Reservation findFirstReservationByOrderByIdDesc() {
		return reservationRepository.findFirstByOrderByIdDesc();
	}
	
//CREATE---------------------------------------------------------------------
	@Transactional
	public void createReservation(Map<String, String> sessionMetadata) {
		final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //日付のフォーマット
		
		Reservation reservation = new Reservation();
		
		Integer carId = Integer.valueOf(sessionMetadata.get("carId"));
		Integer userId = Integer.valueOf(sessionMetadata.get("userId"));
		
		Optional<Car> optionalCar = carRepository.findById(carId);
		//値が存在する場合は変数に代入し、存在しない場合は例外をスローする処理
		Car car = optionalCar.orElseThrow(() -> new EntityNotFoundException("指定されたIDの車両が存在しません。"));
		
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("指定されたIDのユーザーが存在しません。"));
		
		LocalDateTime startDate = LocalDateTime.parse(sessionMetadata.get("startDate"), DATE_TIME_FORMATTER);
		LocalDateTime endDate = LocalDateTime.parse(sessionMetadata.get("endDate"), DATE_TIME_FORMATTER);
		Integer amount = Integer.valueOf(sessionMetadata.get("amount")); 
		
		reservation.setUser(user);
		reservation.setCar(car);
		reservation.setStartDate(startDate);
		reservation.setEndDate(endDate);
		reservation.setAmount(amount);
		reservation.setReservationStatus(ReservationStatus.CONFIRMED);
		
		reservationRepository.save(reservation);
	}
		
}

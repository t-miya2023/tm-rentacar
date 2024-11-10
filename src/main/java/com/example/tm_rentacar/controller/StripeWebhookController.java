package com.example.tm_rentacar.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.tm_rentacar.service.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

@Controller
public class StripeWebhookController {
	private final StripeService stripeService;
	
	@Value("${stripe.webhook-secret}")
	private String webhookSecret;
	
	public StripeWebhookController(StripeService stripeService) {
		this.stripeService = stripeService;
	}
	
	@PostMapping("/stripe/webhook")
	public ResponseEntity<String> webhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
		Event event = null;
		
		try {
			//リクエストの署名を検証し、認証済みのイベントを構築する。webhookSecretが一致しない場合は例外にスローされる。
			event = Webhook.constructEvent(payload, sigHeader, webhookSecret);
		}catch(SignatureVerificationException e) {
			System.out.println("Webhookの署名シークレットが正しくありません。");
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		//イベントタイプがcheckout.session.completedであるなら
		if("checkout.session.completed".equals(event.getType())) {
			//DBに登録するメソッド
			stripeService.processSessionCompleted(event);
		}
		//Webhookリクエストに対するレスポンスを返す
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}

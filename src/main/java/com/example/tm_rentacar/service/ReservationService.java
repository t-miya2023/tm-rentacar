package com.example.tm_rentacar.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.tm_rentacar.entity.Reservation;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.repository.ReservationRepository;

@Service
public class ReservationService {
	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
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
}

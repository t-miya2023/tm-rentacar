package com.example.tm_rentacar.service;

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
}

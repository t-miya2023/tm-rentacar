package com.example.tm_rentacar.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.tm_rentacar.entity.Reservation;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.security.UserDetailsImpl;
import com.example.tm_rentacar.service.ReservationService;

@Controller
public class ReservationController {
	private final ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	
	@GetMapping("/reservations")
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
						@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
						Model model)
	{
		User user = userDetailsImpl.getUser();
		Page<Reservation> reservationPage = reservationService.findReservationByUserOrderByCreatedAtDesc(user, pageable);
		
		model.addAttribute("reservationPage", reservationPage);
		
		return "reservations/index";
 	}
}

package com.example.tm_rentacar.controller;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tm_rentacar.dto.ReservationDto;
import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.entity.Reservation;
import com.example.tm_rentacar.entity.User;
import com.example.tm_rentacar.form.ReservationInputForm;
import com.example.tm_rentacar.security.UserDetailsImpl;
import com.example.tm_rentacar.service.CarService;
import com.example.tm_rentacar.service.ReservationService;
import com.example.tm_rentacar.service.StripeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
	private final ReservationService reservationService;
	private final CarService carService;
	private final StripeService stripeService;
	
	public ReservationController(ReservationService reservationService, CarService carService, StripeService stripeService) {
		this.reservationService = reservationService;
		this.carService = carService;
		this.stripeService = stripeService;
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
	
	@PostMapping("/cars/{id}/reservations/input")
	public String input(@PathVariable(name = "id") Integer id,
						@ModelAttribute @Validated ReservationInputForm reservationInputForm,
						BindingResult bindingResult,
						RedirectAttributes redirectAttributes,
						HttpSession httpSession,
						Model model)
	{
		Optional<Car> optionalCar = carService.findCarById(id);
		
		if(optionalCar.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "対象車両が存在しません。");
			
			return "redirect:/cars";
		}
		
		//利用日時を取得
		LocalDateTime startDate = reservationInputForm.getStartDate();
		LocalDateTime endDate = reservationInputForm.getEndDate();
	
		Car car = optionalCar.get();
		
		//フォームのエラーチェック
		if(startDate != null && endDate != null && !reservationService.isStartBeforeEnd(startDate, endDate)) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "startDate", "利用開始日時は利用終了日時よりも前を選択してください。");
			bindingResult.addError(fieldError);
		}
		
		//エラーがあれば下のページに戻す
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("car", car);
			model.addAttribute("reservationInputForm", reservationInputForm);
			model.addAttribute("errorMessage", "予約内容に不備があります。");
			
			return "cars/show";
		}
		
		//料金計算
		BigDecimal rentalRate = car.getRentalRate();
		Integer amount = reservationService.caluculateAmount(startDate, endDate, rentalRate);
		
		ReservationDto reservationDto = new ReservationDto(car.getId(), startDate, endDate, amount);
		
		httpSession.setAttribute("reservationDto", reservationDto);
		
		return "redirect:/reservations/confirm";
		
	}
	
	@GetMapping("/reservations/confirm")
	public String confirm(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
						RedirectAttributes redirectAttributes, 
						HttpSession httpSession, 
						Model model)
	{
		//セッションからDTOを取得する
		ReservationDto reservationDto = (ReservationDto)httpSession.getAttribute("reservationDto");
	
		if(reservationDto == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "セッションがタイムアウトしました。もう一度予約内容を入力してください。");
		
			return "redirect:/cars";
		}
		
		User user = userDetailsImpl.getUser();
		
		String sessionId = stripeService.createStripeSession(reservationDto, user); 
		
		model.addAttribute("reservationDto", reservationDto);
		model.addAttribute("sessionId", sessionId);
		
		return "reservations/confirm";
	}
	
//	@PostMapping("/reservations/create")
//	public String create(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, 
//						RedirectAttributes redirectAttributes,
//						HttpSession httpSession)
//	{
//		//セッションからDTOを取得
//		ReservationDto reservationDto = (ReservationDto)httpSession.getAttribute("reservationDto");
//		
//		if(reservationDto == null) {
//			redirectAttributes.addFlashAttribute("errorMessage", "セッションがタイムアウトしました。もう一度予約内容を入力してください。");
//		
//			return "redirect:/cars";
//		}
//		
//		User user =userDetailsImpl.getUser();
//		reservationService.createReservation(reservationDto, user);
//		
//		//セッションからDTOを削除する
//		httpSession.removeAttribute("reservationDto");
//		
//		return "redirect:/reservations?reserved";
//	}
	
}

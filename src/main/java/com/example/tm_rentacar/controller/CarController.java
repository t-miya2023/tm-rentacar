package com.example.tm_rentacar.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.enums.CarType;
import com.example.tm_rentacar.service.CarService;

@Controller
@RequestMapping("/cars")
public class CarController {
	private final CarService carService;
	
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
						@RequestParam(name = "type", required = false) CarType type,
						@RequestParam(name = "rentalRate", required = false) BigDecimal rentalRate,
						@RequestParam(name = "order", required = false) String order,
						@PageableDefault(page = 0, size = 10, sort =  "id", direction = Direction.ASC) Pageable pageable,
						Model model)
	{
		Page<Car> carPage;
		
		if(keyword != null && !keyword.isEmpty()) {
			if(order != null && order.equals("priceAsc")) {
				carPage = carService.findCarByMakeLikeOrModelLikeOrderByRentalRateAsc(keyword, keyword, pageable);
			} else {
				carPage = carService.findCarByMakeLikeOrModelLikeOrderByCreatedAtDesc(keyword, keyword, pageable);
			}
		}else if(type != null) {
			if(order != null && order.equals("priceAsc")) {
				carPage = carService.findCarByTypeOrderByRentalRateAsc(type, pageable);
			}else {
				carPage = carService.findCarByTypeOrderByCreatedAtDesc(type, pageable);
			}
		}else if(rentalRate != null) {
			if(order != null && order.equals("priceAsc")) {
				carPage = carService.findCarByRentalRateLessThanEqualOrderByRentalRateAsc(rentalRate, pageable);
			}else {
				carPage = carService.findCarByRentalRateLessThanEqualOrderByCreatedAtDesc(rentalRate, pageable);
			}
		}else {
			if(order != null && order.equals("priceAsc")) {
				carPage = carService.findAllCarByOrderByRentalRateAsc(pageable);
			}else {
				carPage = carService.findAllCarByOrderByCreatedAtDesc(pageable);
			}			
		}
		model.addAttribute("carPage", carPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);
		model.addAttribute("rentalRate", rentalRate);
		model.addAttribute("carType", CarType.values());
		model.addAttribute("order", order);
		
		return "cars/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		Optional<Car> optionalCar = carService.findCarById(id);
		
		if(optionalCar.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "対象の車両が存在しません。");
		
			return "redirect:/cars";
		}
		
		Car car = optionalCar.get();
		model.addAttribute("car", car);
		
		return "cars/show";
	}
}

package com.example.tm_rentacar.controller;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
						@PageableDefault(page = 0, size = 10, sort =  "id", direction = Direction.ASC) Pageable pageable,
						Model model)
	{
		Page<Car> carPage;
		
		if(keyword != null && !keyword.isEmpty()) {
			carPage = carService.findCarByMakeLikeOrModelLike(keyword, keyword, pageable);
		}else if(type != null) {
			carPage = carService.findCarByType(type, pageable);
		}else if(rentalRate != null) {
			carPage = carService.findCarByRentalRateLessThanEqual(rentalRate, pageable);
		}else {
			carPage = carService.findAllCars(pageable);
		}
		
		model.addAttribute("carPage", carPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);
		model.addAttribute("rentalRate", rentalRate);
		model.addAttribute("carType", CarType.values());
		
		return "cars/index";
	}
}

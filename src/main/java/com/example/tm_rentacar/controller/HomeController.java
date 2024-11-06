package com.example.tm_rentacar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.service.CarService;

@Controller
public class HomeController {
	private final CarService carService;
	
	public HomeController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<Car> newCars = carService.findTop10CarsByCreatedAtDesc();
		model.addAttribute("newCars", newCars);
		
		return "index";
	}
}

package com.example.tm_rentacar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.service.CarService;

@Controller
@RequestMapping("/admin/cars")
public class AdminCarsController {
	private final CarService carService;
	
	public AdminCarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Car> cars = carService.findAllCars();
		model.addAttribute("cars", cars);
		
		return "admin/cars/index";
	}
}

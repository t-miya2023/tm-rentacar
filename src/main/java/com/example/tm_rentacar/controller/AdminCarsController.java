package com.example.tm_rentacar.controller;

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
import com.example.tm_rentacar.service.CarService;

@Controller
@RequestMapping("/admin/cars")
public class AdminCarsController {
	private final CarService carService;
	
	public AdminCarsController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,
						@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
						Model model) {
		
		Page<Car> cars ;
		
		if(keyword != null && !keyword.isEmpty()) {
			cars = carService.findCarByModelLike(keyword, pageable);
		} else {
			cars = carService.findAllCars(pageable);
		}
		
		model.addAttribute("cars", cars);
		model.addAttribute("keyword", keyword);
		
		return "admin/cars/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes, Model model) {
		
		Optional<Car> optionalCar = carService.findCarById(id);
		
		if(optionalCar.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "車両が存在しません。");
			return "redirect:/admin/cars";
		}
		
		Car car = optionalCar.get();
		model.addAttribute("car", car);
		
		return "admin/cars.show";
	}
}

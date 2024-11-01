package com.example.tm_rentacar.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.enums.CarStatus;
import com.example.tm_rentacar.enums.CarType;
import com.example.tm_rentacar.form.CarRegisterForm;
import com.example.tm_rentacar.form.CarUpdateForm;
import com.example.tm_rentacar.service.CarImageService;
import com.example.tm_rentacar.service.CarService;

@Controller
@RequestMapping("/admin/cars")
public class AdminCarsController {
	private final CarService carService;
	private final CarImageService carImageService;
	
	public AdminCarsController(CarService carService, CarImageService carImageService) {
		this.carService = carService;
		this.carImageService = carImageService;
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
		
		return "admin/cars/show";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("carRegisterForm", new CarRegisterForm());
		model.addAttribute("carType", CarType.values());
		model.addAttribute("carStatus", CarStatus.values());
		
		return "admin/cars/register";
	}
	
	@PostMapping("/create")
	public String create(@ModelAttribute @Validated CarRegisterForm carRegisterForm,
						BindingResult bindingResult,
						RedirectAttributes redirectAttributes,
						Model model)
	{
		if(bindingResult.hasErrors()) {
			model.addAttribute("carRegisterForm", carRegisterForm);
			model.addAttribute("carType", CarType.values());
			model.addAttribute("carStatus", CarStatus.values());
			
			return "admin/cars/register";
		}
		
		carService.createCar(carRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "車両登録が完了しました。");
		
		return "redirect:/admin/cars";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable(name = "id") Integer id,
				RedirectAttributes redirectAttributes,
				Model model)
	{
		Optional<Car> optionalCar = carService.findCarById(id);
		
		if(optionalCar.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "対象の車両が存在しません。");
			
			return "redirect:/admin/cars";
		}
		
		Car car = optionalCar.get();
		CarUpdateForm carUpdateForm = new CarUpdateForm(car.getMake(), car.getModel(), car.getYear(), car.getLicensePlate(), car.getType(), car.getRentalRate(), car.getStatus(), null);
		
		model.addAttribute("car", car);
		model.addAttribute("carUpdateForm", carUpdateForm);
		model.addAttribute("carType", CarType.values());
		model.addAttribute("carStatus", CarStatus.values());
		
		return "admin/cars/edit";
	}
	
	@DeleteMapping("/images/{id}/delete")
	public ResponseEntity<Void> deleteImage(@PathVariable(name = "id") Integer id) {
	    carImageService.deleteCarImageById(id);  // IDに基づき画像を削除
	    return ResponseEntity.ok().build();
	}

}

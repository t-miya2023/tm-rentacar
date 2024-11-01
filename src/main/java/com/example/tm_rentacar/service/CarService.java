package com.example.tm_rentacar.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.form.CarRegisterForm;
import com.example.tm_rentacar.form.CarUpdateForm;
import com.example.tm_rentacar.repository.CarRepository;

@Service
public class CarService {
	private final CarRepository carRepository;
	private final CarImageService carImageService;
	
	public CarService(CarRepository carRepository, CarImageService carImageService) {
		this.carRepository = carRepository;
		this.carImageService = carImageService;
	}
	
	//全ての車を取得する(ページネーションありで)
	public Page<Car> findAllCars(Pageable pageable) {
		return carRepository.findAll(pageable);
	}
	
	//指定されたキーワードの車種名を取得するメソッド
	public Page<Car> findCarByModelLike(String keyword, Pageable pageable) {
		return carRepository.findByModelLike("%" + keyword + "%", pageable);
	}
	
	//指定したIDを持つ車を取得するメソッド
	public Optional<Car> findCarById(Integer id){
		return carRepository.findById(id);
	}
	
	//Carのレコード数を取得する
	public long countCars() {
		return carRepository.count();
	}
	
	//idの大きなCarを取得する
	public Car findFirstCarByOrderByIdDesc() {
		return carRepository.findFirstByOrderByIdDesc();
	}
	
	
	//Createメソッド
	@Transactional
	public void createCar(CarRegisterForm carRegisterForm) {
		Car car = new Car();
		
		car.setMake(carRegisterForm.getMake());
		car.setModel(carRegisterForm.getModel());
		car.setYear(carRegisterForm.getYear());
		car.setLicensePlate(carRegisterForm.getLicensePlate());
		car.setType(carRegisterForm.getType());
		car.setRentalRate(carRegisterForm.getRentalRate());
		car.setStatus(carRegisterForm.getStatus());
		
		carRepository.save(car);
	
	//画像ファイルがある場合は追加で処理	
		carImageService.saveCarImages(car, carRegisterForm.getImageFiles());
		
	}
	//updateメソッド
	@Transactional
	public void updateCar(CarUpdateForm carUpdateForm, Car car) {
		car.setMake(carUpdateForm.getMake());
		car.setModel(carUpdateForm.getModel());
		car.setYear(carUpdateForm.getYear());
		car.setLicensePlate(carUpdateForm.getLicensePlate());
		car.setType(carUpdateForm.getType());
		car.setRentalRate(carUpdateForm.getRentalRate());
		car.setStatus(carUpdateForm.getStatus());
		
		carRepository.save(car);
	
	//画像ファイルがある場合は追加で処理	
//		car.getImages().clear();
		carImageService.saveCarImages(car, carUpdateForm.getImageFiles());
	}

}

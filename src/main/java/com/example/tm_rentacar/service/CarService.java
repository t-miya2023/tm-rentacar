package com.example.tm_rentacar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.repository.CarRepository;

@Service
public class CarService {
	private final CarRepository carRepository;
	
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	
	//全ての車を取得する
	public List<Car> findAllCars() {
		return carRepository.findAll();
	}
}

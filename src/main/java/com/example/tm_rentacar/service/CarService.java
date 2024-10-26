package com.example.tm_rentacar.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.repository.CarRepository;

@Service
public class CarService {
	private final CarRepository carRepository;
	
	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
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
}

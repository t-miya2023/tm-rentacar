package com.example.tm_rentacar.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.Car;
import com.example.tm_rentacar.enums.CarType;

public interface CarRepository extends JpaRepository<Car, Integer> {
	public Page<Car> findByModelLike(String keyword, Pageable pageable);
	public Car findFirstByOrderByIdDesc();
	public Page<Car> findByMakeLikeOrModelLike(String makeKeyword, String modelKeyword, Pageable pageable);
	public Page<Car> findByType(CarType type, Pageable pageable);
	public Page<Car> findByPriceLessThsnEqual(BigDecimal price, Pageable pageable);
}

package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}

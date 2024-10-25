package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {

}

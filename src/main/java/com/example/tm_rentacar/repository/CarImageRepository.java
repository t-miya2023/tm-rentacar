package com.example.tm_rentacar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tm_rentacar.entity.CarImage;

public interface CarImageRepository extends JpaRepository<CarImage, Integer> {
	 @Query("SELECT ci.imageUrl FROM CarImage ci WHERE ci.car.id = :carId")
	List<String> findImageUrlsByCarId(@Param("carId") Integer carId);

}

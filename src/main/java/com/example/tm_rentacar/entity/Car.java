package com.example.tm_rentacar.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.example.tm_rentacar.enums.CarStatus;
import com.example.tm_rentacar.enums.CarType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cars")
@Data
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "make")
	private String make;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "year")
	private String year;
	
	@Column(name = "license_plate")
	private String licensePlate;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="type")
	private CarType type;
	
	@Column(name = "rental_rate")
	private BigDecimal rentalRate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CarStatus status;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CarImage> images;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
}

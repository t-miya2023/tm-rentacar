package com.example.tm_rentacar.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "car_images")
@Data
public class CarImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	@ToString.Exclude
	private Car car;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
}

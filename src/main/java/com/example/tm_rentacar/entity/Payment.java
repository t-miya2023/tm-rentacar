package com.example.tm_rentacar.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.example.tm_rentacar.enums.PaymentMethod;
import com.example.tm_rentacar.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payments")
@Data
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	@Column(name = "payment_method")
	private PaymentMethod paymentMethod;
	
	@Column(name = "payment_date")
	private LocalDateTime paymentDate;
	
	@Column(name = "status")
	private PaymentStatus paymentStatus;
	
	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
}

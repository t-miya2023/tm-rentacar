package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}

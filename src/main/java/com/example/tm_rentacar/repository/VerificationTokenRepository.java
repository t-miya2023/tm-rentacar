package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.VerificationToken;

public interface VerificationTokenRepository  extends JpaRepository<VerificationToken, Integer>{
	public VerificationToken findByToken(String token); 
}
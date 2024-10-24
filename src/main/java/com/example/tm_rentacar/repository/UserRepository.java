package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
}

package com.example.tm_rentacar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tm_rentacar.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	public Role findByName(String name);
}

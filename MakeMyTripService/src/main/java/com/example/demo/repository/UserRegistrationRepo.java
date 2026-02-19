package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserRegistration;

public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Long>{

	public UserRegistration findByEmail(String email);

}

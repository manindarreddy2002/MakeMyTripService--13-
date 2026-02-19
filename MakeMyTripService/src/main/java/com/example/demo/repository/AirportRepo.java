package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Airport;

public interface AirportRepo extends JpaRepository<Airport, Long>{

	public List<Airport> findByCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(String code,String name,String city);

}

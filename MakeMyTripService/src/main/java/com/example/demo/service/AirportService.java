package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Airport;

public interface AirportService {

	List<Airport> findByListofAirports(String query);

}

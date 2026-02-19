package com.example.demo.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.AirportController;
import com.example.demo.entity.Airport;
import com.example.demo.repository.AirportRepo;
import com.example.demo.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

	@Autowired
	private AirportRepo airportRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(AirportServiceImpl.class);

	@Override
	public List<Airport> findByListofAirports(String query) {
		logger.info("findByListofAirports servieimpl started");

		List<Airport> findByListAirPorts = airportRepo
				.findByCodeContainingIgnoreCaseOrNameContainingIgnoreCaseOrCityContainingIgnoreCase(query, query,
						query);

		System.err.println("listofAirports" + findByListAirPorts.toString());
		logger.info("findByListofAirports servieimpl ended..");
		return findByListAirPorts;
	
	}}

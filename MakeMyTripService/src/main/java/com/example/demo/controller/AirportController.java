package com.example.demo.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Airport;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.service.AirportService;
import com.example.demo.utility.Constants;

@RestController
public class AirportController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(AirportController.class);
	
	@Autowired
	private AirportService airportService;

	
	@GetMapping("/searchairports")
     public ResponseEntity<ResponseMessageDto> searchByairports(@RequestParam String query) {
		logger.info("searchairports controller started");
		
		try {
		
			List<Airport> byListofAirports = airportService.findByListofAirports(query);
			logger.warn("searchairports findByListofAirports getfailed..");
			logger.debug("searchairports findByListofAirports get query failed",byListofAirports);
		
		if(byListofAirports.isEmpty()) {
			
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Airports are not found"));
			
		}else {
			logger.info("searchairports controller ended");
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Airports are getting successfully", byListofAirports));
			
		}}catch (Exception e) {
			logger.error("searchairports internal error");
			logger.warn("searchairports internal error");
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Internal Server error"));
			
		}
		
	}
	
}
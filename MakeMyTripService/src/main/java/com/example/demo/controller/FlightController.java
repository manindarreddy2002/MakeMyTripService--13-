package com.example.demo.controller;


import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flight;
import com.example.demo.model.BookingReqDto;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.service.FlightService;
import com.example.demo.utility.Constants;

@RestController
public class FlightController {
	
	@Autowired
	private FlightService flightService;



	@GetMapping("/searchByFlights")
     public ResponseEntity<ResponseMessageDto> searchByFlights(
    		 
    		 @RequestParam String from,
    		 @RequestParam String to,
    		 @RequestParam LocalDate travelDate,
    		 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    		 @RequestParam int passengers) {
		
		try {
			 List<Flight> searchFlights = flightService.findByFlights(from,to,travelDate,passengers);
		
		if(searchFlights.isEmpty()) {
			
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Flights are  not avalible"));
		}else {
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Flights are getting successfully", searchFlights));
		}}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE, "Internal Server error"));
			
		}
		
	}
	

	
	
	
	

	
	
}

package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flight;
import com.example.demo.model.BookingReqDto;

public interface FlightService {

	List<Flight> findByFlights(String from, String to, LocalDate travelDate, int passengers);

	

}

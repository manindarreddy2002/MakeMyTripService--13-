package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.entity.Flight;
import com.example.demo.model.BookingReqDto;
import com.example.demo.repository.FlightRepo;
import com.example.demo.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepo flightRepo;

	@Override
	public List<Flight> findByFlights(String from, String to, LocalDate travelDate, int passengers) {

		return flightRepo.findByFromCityIgnoreCaseAndToCityIgnoreCaseAndTravelDateAndAvailableSeatsGreaterThanEqual(
				from, to, travelDate, passengers);

	}

}

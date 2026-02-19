package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Flight;

public interface FlightRepo extends JpaRepository<Flight, Long>{
	
	
	List<Flight> findByFromCityIgnoreCaseAndToCityIgnoreCaseAndTravelDateAndAvailableSeatsGreaterThanEqual(
            String fromCity,
            String toCity,
            LocalDate travelDate,
            int passengers
    );
	
	
//	@Query("SELECT f FROM Flight f WHERE LOWER(f.fromCity) = LOWER(:fromCity) AND LOWER(f.toCity) = LOWER(:toCity) AND f.travelDate = :travelDate AND f.availableSeats >= :passengers")
//	List<Flight> searchFlights(
//	        @Param("fromCity") String fromCity,
//	        @Param("toCity") String toCity,
//	        @Param("travelDate") LocalDate travelDate,
//	        @Param("passengers") int passengers
//	);
//
//	@Query(value = "SELECT * FROM flight WHERE LOWER(from_city) = LOWER(:fromCity) AND LOWER(to_city) = LOWER(:toCity) AND travel_date = :travelDate AND available_seats >= :passengers",
//	        nativeQuery = true)
//	List<Flight> searchFlightsNative(
//	        @Param("fromCity") String fromCity,
//	        @Param("toCity") String toCity,
//	        @Param("travelDate") LocalDate travelDate,
//	        @Param("passengers") int passengers
//	);


}

package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "flightNumber")
	private String flightNumber;
	
	@Column(name = "airline")
	private String airline;
	
	@Column(name = "fromCity")
	private String fromCity;
	
	@Column(name = "toCity")
	private String toCity;
	
	@Column(name = "travelDate")
	private LocalDate travelDate;
	
	@Column(name = "departureTime")
	private LocalDateTime departureTime;
	
	@Column(name = "arrivalTime")
	private LocalDateTime arrivalTime;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "availableSeats")
	private int availableSeats;
}

package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaggageResponse {

	private Long bookingId;
	private double weight;
	private double extraCharge;
	private String message;
	

}

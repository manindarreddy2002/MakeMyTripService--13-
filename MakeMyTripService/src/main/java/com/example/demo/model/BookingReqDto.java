package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingReqDto {

	private Long flightId;
	private int passengers;
	private String passengerName;
	private String email;
	private String phone;

}

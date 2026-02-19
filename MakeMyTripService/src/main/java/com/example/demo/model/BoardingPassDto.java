package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPassDto {

    private Long bookingId;
    private String passengerName;
    private String email;
    private String phone;

    private String flightNumber;
    private String fromCity;
    private String toCity;
    private String travelDate;
    private String departureTime;

    private String seatNumber;
    private String gateNumber = "G-12"; // static/custom value
}

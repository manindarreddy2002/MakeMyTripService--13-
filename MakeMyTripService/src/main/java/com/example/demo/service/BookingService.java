package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Booking;
import com.example.demo.model.BoardingPassDto;
import com.example.demo.model.BookingReqDto;
import com.example.demo.model.CheckReqDto;

public interface BookingService {

	public Booking bookFlight(BookingReqDto bookingReqDto);

	public String bookCancelFlight(Long bookId);

	public Booking getByBookFlightId(Long bookId);

	public List<Booking> getByEmailListBookingHistroy(String email);



}

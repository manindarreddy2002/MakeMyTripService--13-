package com.example.demo.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Booking;
import com.example.demo.model.BookingReqDto;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.service.BookingService;
import com.example.demo.utility.Constants;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	  @PostMapping("/bookingflightticket")
	     public ResponseEntity<ResponseMessageDto> bookingFlights(@RequestBody BookingReqDto bookingReqDto) {
			
				Booking bookFlight = bookingService.bookFlight(bookingReqDto);
			
			if(bookFlight!=null) {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Flight booking successfully", bookFlight));
			}else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Flight booking failed"));
			}
			
		}
	 

		     @DeleteMapping("/bookingflightCancel/{bookId}")
		     public ResponseEntity<ResponseMessageDto> cancelFlight(@PathVariable Long bookId) {
				
					 String bookCancelFlight = bookingService.bookCancelFlight(bookId);
				
				if(bookCancelFlight!=null) {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Flight Cancel successfully", bookCancelFlight));
				}else {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Flight booking failed"));
				}
				
			} 
	  

		     @GetMapping("/booking/{bookId}")
		     public ResponseEntity<ResponseMessageDto> getByBookingTicket(@PathVariable Long bookId) {
				
					  Booking byBookFlightId = bookingService.getByBookFlightId(bookId);
				
				if(byBookFlightId!=null) {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Flight ticket get successfully", byBookFlightId));
				}else {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Flight ticket get failed"));
				}
				
			} 
		     
//		     @GetMapping("/bookingemail")  // Request Param form Data
		     @GetMapping("/bookinghistory/{email}")  //@PathVariable along with Url
		     public ResponseEntity<ResponseMessageDto> getByBookingTicket(@PathVariable String email) {
//		     public ResponseEntity<ResponseMessageDto> getByBookingTicket(@RequestParam String email) {
				
				 List<Booking> byEmailListBookingHistroy = bookingService.getByEmailListBookingHistroy(email);
				
				if(byEmailListBookingHistroy!=null) {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Flight Booking deatils history get successfully", byEmailListBookingHistroy));
				}else {
					return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Flight Booking deatils history get failed"));
				}
				
			} 
		     
//		     http://localhost:7777/bookinghistory/Sunil@gmail.com
		     
}

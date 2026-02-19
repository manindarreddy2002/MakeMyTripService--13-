package com.example.demo.exceptions;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.model.ErrorResponseDto;
import com.example.demo.utility.Constants;

//@ControllerAdvice
@RestControllerAdvice
public class MakeMytripGlobalExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<Object> handleFlightnotfound(FlightNotFoundException ex) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Error:", "FlightNotFound");
		hm.put("Detailed Message:", ex.getLocalizedMessage());
		hm.put("Timestamp:", System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"FLIGHT-NOTFOUND", hm);
		return ResponseEntity.ok(err);

//		List<String> list = new ArrayList<>();
//		list.add("Error:FlightNotFound");
//		list.add("Detailed Message:" + ex.getLocalizedMessage());
//		list.add("Timestamp:" + System.currentTimeMillis());
//	    ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "FLIGHT-NOTFOUND", list);
//		return ResponseEntity.ok(err);

	}

	@ExceptionHandler(SeatsNotFoundException.class)
	public ResponseEntity<Object> handleSeatsnotfound(SeatsNotFoundException ex) {

		List<String> list = new ArrayList<>();
		list.add("Error:SeatsNotFound");
		list.add("Detailed Message:" + ex.getLocalizedMessage());
		list.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"SEATS-NOT-FOUND", list);
		return ResponseEntity.ok(err);

	}
	
	
	
	@ExceptionHandler(BookingIDNotFoundException.class)
	public ResponseEntity<Object> handleBookingnotfound(BookingIDNotFoundException ex) {

		List<String> list = new ArrayList<>();
		list.add("Error:BookingIDNotFoundException");
		list.add("Detailed Message:" + ex.getLocalizedMessage());
		list.add("Timestamp:" + System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"BOOKING-NOT-FOUND", list);
		return ResponseEntity.ok(err);
	}
	
	@ExceptionHandler(CancelBookingException.class)
	public ResponseEntity<Object> CancelBookingExce(CancelBookingException ex) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Error:", "CancelBookingException");
		hm.put("Detailed Message:", ex.getLocalizedMessage());
		hm.put("Timestamp:", System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"CANCELBOOIKG-NOTFOUND", hm);
		return ResponseEntity.ok(err);
	
	}
	
	@ExceptionHandler(FlightTicketCancel.class)
	public ResponseEntity<Object> CancelFlightExce(FlightTicketCancel ex) {

		HashMap<String, Object> hm = new HashMap<>();
		hm.put("Error:", "FlightTicketCancelException");
		hm.put("Detailed Message:", ex.getLocalizedMessage());
		hm.put("Timestamp:", System.currentTimeMillis());
		ErrorResponseDto err = new ErrorResponseDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
				"CANCELBOOIKG-NOTFOUND", hm);
		return ResponseEntity.ok(err);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}

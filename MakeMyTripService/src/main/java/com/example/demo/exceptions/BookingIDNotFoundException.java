package com.example.demo.exceptions;

public class BookingIDNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BookingIDNotFoundException(String str) {
		
		super(str);
		
	}
	
}

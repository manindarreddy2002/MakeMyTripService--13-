package com.example.demo.exceptions;

public class CancelBookingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CancelBookingException(String str) {
		
		super(str);
		
	}

}

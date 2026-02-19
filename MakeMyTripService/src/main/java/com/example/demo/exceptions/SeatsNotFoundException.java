package com.example.demo.exceptions;

public class SeatsNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeatsNotFoundException(String msg) {
		
		super(msg);
		
	}

}

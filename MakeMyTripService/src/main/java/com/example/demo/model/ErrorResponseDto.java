package com.example.demo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
	
	private Integer statuscode;
	private String status;
	private String message;
	private List<?> list;
	private HashMap<String, Object> map;
	
	public ErrorResponseDto(Integer statuscode, String status, String message, List<?> list) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.list = list;
	}

	public ErrorResponseDto(Integer statuscode, String status, String message, HashMap<String, Object> map) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.message = message;
		this.map = map;
	}

	

}

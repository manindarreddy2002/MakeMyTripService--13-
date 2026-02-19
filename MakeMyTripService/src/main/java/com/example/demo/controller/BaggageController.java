package com.example.demo.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BaggageReqDto;
import com.example.demo.model.BaggageResponse;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.service.BaggageService;
import com.example.demo.utility.Constants;

@RestController
public class BaggageController {
	
	@Autowired
	private BaggageService baggageService;

	     @PostMapping("/checklaugage")
	     public ResponseEntity<ResponseMessageDto> checklaugages(@RequestBody BaggageReqDto baggageReqDto) {
			
	    	 	  BaggageResponse checkLaugageWeight = baggageService.checkLaugageWeight(baggageReqDto);
			
			if(checkLaugageWeight!=null) {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Laugage checked successfully", checkLaugageWeight));
			}else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Laugage checked failed"));
			}
			
		}
	
	
	
	

}

package com.example.demo.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoardingPassDto;
import com.example.demo.model.CheckReqDto;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.service.CheckBoardService;
import com.example.demo.utility.Constants;

@RestController
public class CheckInController {
	
	@Autowired
	private CheckBoardService checkBoardService;

	     @PostMapping("/checkInBoardingpass")
	     public ResponseEntity<ResponseMessageDto> checkIn(@RequestBody CheckReqDto checkReqDto) {
			
				 BoardingPassDto checkBoarding = checkBoardService.doCheckIn(checkReqDto);
			
			if(checkBoarding!=null) {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "checkIn Boarding successfully", checkBoarding));
			}else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "checkIn Boarding failed"));
			}
			
		}
	 
	
	

}

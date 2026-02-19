package com.example.demo.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UserRegistration;
import com.example.demo.model.LoginReqDto;
import com.example.demo.model.ResponseMessageDto;
import com.example.demo.model.UserRegisterDto;
import com.example.demo.model.UserRegistrationDto;
import com.example.demo.service.UserRegistrationService;
import com.example.demo.utility.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "UserRegisterController Controller", description = "Register Create, read, update and delete")
@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	@Operation(summary = "Post employee", description = "create new user")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user created successfully"),
			@ApiResponse(responseCode = "400", description = "Badrequest"),
			@ApiResponse(responseCode = "500", description = "Internal Server error") })
	@PostMapping(Constants.USERREGISTER)
	public ResponseEntity<ResponseMessageDto> makeMyTripRegistartion(
			@RequestBody UserRegistrationDto userRegistrationDto) {

		try {

			if (userRegistrationDto == null || userRegistrationDto.getEmail() == null
					|| userRegistrationDto.getEmail().isEmpty() || userRegistrationDto.getPassword() == null
					|| userRegistrationDto.getPassword().isBlank()) {

				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"email and password cannot be empty or null"));
			}

			UserRegistration insertUserRegister = userRegistrationService.insertUserRegister(userRegistrationDto);

			if (insertUserRegister != null) {

				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Makemytrip user registration successfully", insertUserRegister));
			} else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Makemytrip user registration failed", insertUserRegister));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"Internal Server error"));
		}
	}

	@PostMapping(Constants.USERLOGIN)
	public ResponseEntity<ResponseMessageDto> makeMyTripLogin(@RequestBody LoginReqDto loginReqDto) {

		try {

			if (loginReqDto == null || loginReqDto.getEmail() == null || loginReqDto.getEmail().isEmpty()
					|| loginReqDto.getPassword() == null || loginReqDto.getPassword().isBlank()) {

				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"email and password cannot be empty or null"));
			}

			UserRegistration insertUserRegister = userRegistrationService.checkLoginCredentials(loginReqDto);

			if (insertUserRegister != null) {

				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Makemytrip user Login successfully", insertUserRegister));
			} else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Makemytrip user Login failed", insertUserRegister));
			}
		} catch (Exception e) {
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"Internal Server error"));
		}
	}

	@PostMapping("/userregisterwithmultipartfiles")
	public ResponseEntity<ResponseMessageDto> makeMyTripRegistartion(@RequestParam String jsonData,
			@RequestParam MultipartFile[] filess) {

		try {

			UserRegistrationDto userRegistrationDto = new ObjectMapper().readValue(jsonData, UserRegistrationDto.class);

			UserRegistration insertUserRegister = userRegistrationService
					.insertUserRegisterwithMultipartfiles(userRegistrationDto, filess);

			if (insertUserRegister != null) {

				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
						"Makemytrip user registration with miltipartfile saved successfully", insertUserRegister));
			} else {
				return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Makemytrip user registration with miltipartfile saved failed", insertUserRegister));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessageDto(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILURE,
					"Internal Server error"));
		}
	}

	@GetMapping("/register/{id}")
	public UserRegisterDto getUserReg(@PathVariable Long id) {

		UserRegisterDto byUserId = userRegistrationService.getByUserId(id);

		return byUserId;

	}

}

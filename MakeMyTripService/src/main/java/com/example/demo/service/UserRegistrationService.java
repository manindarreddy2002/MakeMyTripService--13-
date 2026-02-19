package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UserRegistration;
import com.example.demo.model.LoginReqDto;
import com.example.demo.model.UserRegisterDto;
import com.example.demo.model.UserRegistrationDto;

public interface UserRegistrationService {

	public UserRegistration insertUserRegister(UserRegistrationDto userRegistrationDto);

	public UserRegistration checkLoginCredentials(LoginReqDto loginReqDto);

	public UserRegistration insertUserRegisterwithMultipartfiles(UserRegistrationDto userRegistrationDto, MultipartFile[] filess);

	public UserRegisterDto getByUserId(Long id);

}

package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDto {
	
	private String firstName;
	private String lastName;
	private long phoneNumber;
	private String email;

}

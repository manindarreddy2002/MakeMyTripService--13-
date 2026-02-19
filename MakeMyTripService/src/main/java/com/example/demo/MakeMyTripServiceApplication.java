package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
        title = "MakeMytrip Booking online Ticket system",
        version = "1.0",
        description = "Register Management App",
        contact = @Contact(name ="Naresh IT Technology",email = "vali@gmail.com")))
@SpringBootApplication
public class MakeMyTripServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeMyTripServiceApplication.class, args);
	}

}

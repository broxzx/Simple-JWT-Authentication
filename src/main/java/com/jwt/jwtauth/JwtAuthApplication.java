package com.jwt.jwtauth;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthApplication {


	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

}

package com.jwt.jwtauth;

import com.jwt.jwtauth.entity.Role;
import com.jwt.jwtauth.entity.UserEntity;
import com.jwt.jwtauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthApplication {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthApplication.class, args);
	}

}

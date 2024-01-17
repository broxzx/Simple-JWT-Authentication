package com.jwt.jwtauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}

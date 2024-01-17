package com.jwt.jwtauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppError extends RuntimeException{
    public AppError(String message) {
        super(message);
    }
}

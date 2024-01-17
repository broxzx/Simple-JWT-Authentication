package com.jwt.jwtauth.service;

import com.jwt.jwtauth.dto.JwtRequest;
import com.jwt.jwtauth.dto.JwtResponse;
import com.jwt.jwtauth.dto.RegistrationUserDto;
import com.jwt.jwtauth.dto.UserDto;
import com.jwt.jwtauth.entity.UserEntity;
import com.jwt.jwtauth.exceptions.AppError;
import com.jwt.jwtauth.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtTokenUtils jwtTokenUtils;
    private final UserService userService;
    private final AuthenticationManager authentication;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            log.info("auth controller passed");
            authentication.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user cannot be authorized");
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String generatedToken = jwtTokenUtils.generateToken(userDetails);
        log.info(generatedToken);

        return ResponseEntity.ok(new JwtResponse(generatedToken));
    }

    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError("passwords don't match"), HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userService.createNewUserEntity(registrationUserDto);

        return ResponseEntity.ok(new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

}

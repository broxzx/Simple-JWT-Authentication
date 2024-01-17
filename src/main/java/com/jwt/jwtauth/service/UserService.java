package com.jwt.jwtauth.service;

import com.jwt.jwtauth.dto.RegistrationUserDto;
import com.jwt.jwtauth.entity.UserEntity;
import com.jwt.jwtauth.exceptions.UserNotFoundException;
import com.jwt.jwtauth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UserNotFoundException("user with username %s was not found".formatted(username))
                );

        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserEntity createNewUserEntity(RegistrationUserDto registrationUserDto) {

        UserEntity userEntity = new UserEntity(
                null,
                registrationUserDto.getUsername(),
                passwordEncoder.encode(registrationUserDto.getPassword()),
                registrationUserDto.getEmail(),
                null
        );

        return userRepository.save(userEntity);
    }
}

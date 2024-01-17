package com.jwt.jwtauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "unsecured data";
    }

    @GetMapping("/secured")
    public String securedData() {
        return "secured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "admin data";
    }

    @GetMapping("/info")
    public String infoData() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return "secured data %s".formatted(name);
    }
}

package com.example.demo.controller;

import com.example.demo.restclient.dto.JwtRequestDto;
import com.example.demo.restclient.dto.JwtResponseDto;
import com.example.demo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public JwtResponseDto authenticate(@RequestBody JwtRequestDto jwtRequestDto) {
        return authService.authenticate(jwtRequestDto);
    }
}

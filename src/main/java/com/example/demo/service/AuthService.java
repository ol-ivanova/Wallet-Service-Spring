package com.example.demo.service;

import com.example.demo.restclient.dto.JwtRequestDto;
import com.example.demo.restclient.dto.JwtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;

    public JwtResponseDto authenticate(JwtRequestDto requestDto) {

    }
}

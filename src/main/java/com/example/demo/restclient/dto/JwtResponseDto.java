package com.example.demo.restclient.dto;

import lombok.Value;

@Value
public class JwtResponseDto {
    String tokenType = "Bearer";
    String jwt;
}
package com.example.demo.model.dto;

import lombok.Value;

@Value
public class JwtResponseDto {
    String tokenType = "Bearer";
    String jwt;
}
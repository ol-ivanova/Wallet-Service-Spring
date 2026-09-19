package com.example.demo.model.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO-класс, использующийся для передачи сообщений об ошибках
 */
@Getter
public class ExceptionResponseDto {
    private final List<String> messages = new ArrayList<>();

    public ExceptionResponseDto(String message) {
        messages.add(message);
    }
}

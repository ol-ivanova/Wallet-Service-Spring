package com.example.demo.exception;

import com.example.demo.model.dto.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обработчик исключений
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.example.demo.controller")
public class RestControllerExceptionHandler {

    /**
     * метод, обрабатывающий исключения, выбрасывающиеся при:
     * 1) (MethodArgumentTypeMismatchException)
     * 2) процессе взаимодействия с клиентом (PlayerException)
     * 3) процессе аутентификации (AuthException)
     * 4) остальных, не пользовательских, ошибках
     * @param ex - exception
     * @return ResponseEntity - ответ
     */
    @ExceptionHandler(value = {PlayerException.class, AuthException.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception ex){
        ResponseEntity<ExceptionResponseDto> response;

        if (ex instanceof PlayerException || ex instanceof PlayerAccountException) {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ExceptionResponseDto(ex.getMessage())
            );
        } else if (ex instanceof AuthException) {
            response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new ExceptionResponseDto(ex.getMessage())
            );
        } else {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ExceptionResponseDto(ex.getMessage())
            );
        }

        return response;
    }
}

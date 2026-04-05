package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BaseRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlayerException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleCustomExceptions(RuntimeException ex){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof PlayerException) {
            status = HttpStatus.CONFLICT;
        }

        /**
         * одно и то же, просто разная запись
         */
//        return ResponseEntity.status(status).body("hello from exception handler");
        return new ResponseEntity<>( "hello from exception handler", status);
    }
}

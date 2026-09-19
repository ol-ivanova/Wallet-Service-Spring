package com.example.demo.exception;

/**
 * Exception для класса AuthService
 */
public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}

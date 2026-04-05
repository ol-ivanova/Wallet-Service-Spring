package com.example.demo.exception;

/**
 * Exception для транзакций
 */
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}

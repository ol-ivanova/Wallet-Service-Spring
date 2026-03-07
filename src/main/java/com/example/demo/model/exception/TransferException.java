package com.example.demo.model.exception;

/**
 * Exception для транзакций
 */
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}

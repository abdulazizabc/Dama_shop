package com.example.dama_shop.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
    public ForbiddenException(String message, Long id) {
        super(message + ": " + id);
    }
}

package com.example.dama_shop.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message,Long id) {
        super(message);
    }

}

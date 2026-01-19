package com.global66practice.domain.exceptions;

public class TraderNotFoundException extends RuntimeException {
    public TraderNotFoundException(String message) {
        super(message);
    }
}

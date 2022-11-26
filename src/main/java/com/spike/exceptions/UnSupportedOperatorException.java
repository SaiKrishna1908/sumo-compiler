package com.spike.exceptions;

public class UnSupportedOperatorException extends Exception {
    String message;

    public UnSupportedOperatorException(String message) {
        super(message);
        this.message = message;
    }
}
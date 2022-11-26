package com.spike.exceptions;

public class InvalidExpression extends Exception {
    String message;

    public InvalidExpression(String message) {
        super();
        this.message = message;
    }
}
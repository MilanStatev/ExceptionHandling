package com.company.oop.cosmetics.exceptions;

public class NumberNotPositiveException extends RuntimeException{
    public NumberNotPositiveException() {
    }

    public NumberNotPositiveException(String message) {
        super(message);
    }
}

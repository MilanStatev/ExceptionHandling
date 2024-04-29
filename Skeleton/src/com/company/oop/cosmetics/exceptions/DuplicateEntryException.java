package com.company.oop.cosmetics.exceptions;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException() {
    }

    public DuplicateEntryException(String message) {
        super(message);
    }
}

package com.dvp.technical_test.infrastructure.exception;

public class MissingFieldsException extends RuntimeException{
    public MissingFieldsException(String message) {
        super(message);
    }
}

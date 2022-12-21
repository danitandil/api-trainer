package com.trainer.exceptions;

public class EmailDuplicatedException extends RuntimeException {
    public EmailDuplicatedException(String message) {
        super(message);
    }
}

package com.trainer.exception;

public class TrainerNotFound extends RuntimeException {
    public TrainerNotFound(String message) {
        super(message);
    }
}

package com.trainer.exceptions;

import java.util.List;

public class RequestException extends RuntimeException {

    RequestException(List<String> messages) {
        super(String.join(",", messages));
    }
}

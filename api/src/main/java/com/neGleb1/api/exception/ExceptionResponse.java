package com.neGleb1.api.exception;

import java.time.ZonedDateTime;

public class ExceptionResponse {
    
    private final String message;
    private final int status;
    private final ZonedDateTime timestamp;

    public ExceptionResponse(String message, int status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}

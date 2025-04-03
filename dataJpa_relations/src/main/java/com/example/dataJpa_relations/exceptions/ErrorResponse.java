package com.example.dataJpa_relations.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private String details;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();  // Set current timestamp
    }

    // Default constructor for JSON deserialization
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

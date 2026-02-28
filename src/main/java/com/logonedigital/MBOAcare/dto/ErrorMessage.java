package com.logonedigital.MBOAcare.dto;

import java.time.LocalDateTime;

public class ErrorMessage {

    private String message;
    private LocalDateTime timestamp;
    private String error;
    private Integer statusCode;

    public ErrorMessage() {
    }

    public ErrorMessage(String message, LocalDateTime timestamp, String error, Integer statusCode) {
        this.message = message;
        this.timestamp = timestamp;
        this.error = error;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}

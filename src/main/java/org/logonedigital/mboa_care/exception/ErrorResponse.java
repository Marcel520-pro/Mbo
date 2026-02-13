package org.logonedigital.mboa_care.exception;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private String error; // ex: "Not Found"
    private int status;

    public ErrorResponse(String message, LocalDateTime now, String reasonPhrase, int value) {
        this.message = message;
        this.timestamp = now;
        this.error = reasonPhrase;
        this.status = value;
    }
}

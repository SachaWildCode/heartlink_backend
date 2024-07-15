package fr.slghive.heartlink.exceptions.format;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private String message;
    private String details;
    private int status;

    public ApiError(LocalDateTime timestamp, String message, String details, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.status = status;
    }
}
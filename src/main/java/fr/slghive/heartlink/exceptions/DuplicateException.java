package fr.slghive.heartlink.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateException extends RuntimeException {
    private final HttpStatus status;

    public DuplicateException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

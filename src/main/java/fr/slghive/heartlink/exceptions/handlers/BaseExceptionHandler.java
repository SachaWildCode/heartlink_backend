package fr.slghive.heartlink.exceptions.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import fr.slghive.heartlink.exceptions.format.ApiError;

public abstract class BaseExceptionHandler {

    protected ResponseEntity<ApiError> buildResponseEntity(
            Exception ex,
            WebRequest request,
            HttpStatus status,
            String message) {
        String path = request.getDescription(false).replace("uri=", "");
        ApiError errorDetails = new ApiError(
                LocalDateTime.now(),
                message != null ? message : ex.getMessage(),
                status.value(),
                path);
        return new ResponseEntity<>(errorDetails, status);
    }

    protected ResponseEntity<ApiError> handleException(
            Exception ex,
            WebRequest request,
            HttpStatus status,
            String message) {
        return buildResponseEntity(ex, request, status, message);
    }
}

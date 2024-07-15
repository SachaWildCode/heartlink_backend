package fr.slghive.heartlink.exceptions.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import fr.slghive.heartlink.exceptions.DuplicateException;
import fr.slghive.heartlink.exceptions.ResourceNotFoundException;
import fr.slghive.heartlink.exceptions.format.ApiError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler extends BaseExceptionHandler {

    // Custom exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {
        return handleException(ex, request, ex.getStatus(), null);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ApiError> handleDuplicateException(
            DuplicateException ex,
            WebRequest request) {
        return handleException(ex, request, ex.getStatus(), null);
    }

    // Global handler for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(
            Exception ex,
            WebRequest request) {
        return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    // Spring security exceptions
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationException(
            InsufficientAuthenticationException ex,
            WebRequest request) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwtException(
            ExpiredJwtException ex,
            WebRequest request) {
        return handleException(
                ex,
                request,
                HttpStatus.UNAUTHORIZED,
                "JWT token has expired");
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(
            JwtException ex,
            WebRequest request) {
        return handleException(
                ex,
                request,
                HttpStatus.BAD_REQUEST,
                "Invalid JWT token");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException(
            BadCredentialsException ex,
            WebRequest request) {
        return handleException(ex, request, HttpStatus.UNAUTHORIZED, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<String> errors = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    if (error instanceof FieldError) {
                        return (((FieldError) error).getField() + ": " + error.getDefaultMessage());
                    } else {
                        return error.getObjectName() + ": " + error.getDefaultMessage();
                    }
                })
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errors);
        return handleException(ex, request, HttpStatus.BAD_REQUEST, errorMessage);
    }
}

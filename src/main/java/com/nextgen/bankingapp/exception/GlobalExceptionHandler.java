package com.nextgen.bankingapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

/**
 * Centralised HTTP error mapping. Keeps controllers free of try/catch and
 * status-code plumbing (Open/Closed: new error types are handled here,
 * without touching controller or service code), and guarantees every
 * endpoint fails the same, predictable way.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(toApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
  }

  private static ApiError toApiError(HttpStatus status, String message, HttpServletRequest request) {
    return new ApiError(Instant.now(), status.value(), status.getReasonPhrase(), message, request.getRequestURI());
  }
}

package com.nextgen.bankingapp.exception;

/**
 * Thrown when a user cannot be located by username. Mapped to a
 * {@code 404 Not Found} response by {@link GlobalExceptionHandler}.
 */
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String username) {
    super("User with username '" + username + "' not found");
  }
}

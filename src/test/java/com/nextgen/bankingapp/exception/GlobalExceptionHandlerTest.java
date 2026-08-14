package com.nextgen.bankingapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    @Test
    void handleUserNotFound_returns404WithApiErrorBody() {
        final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        Mockito.when(request.getRequestURI()).thenReturn("/user/ghost");

        final GlobalExceptionHandler handler = new GlobalExceptionHandler();
        final ResponseEntity<ApiError> response = handler.handleUserNotFound(new UserNotFoundException("ghost"), request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().status());
        assertEquals("/user/ghost", response.getBody().path());
        assertEquals("User with username 'ghost' not found", response.getBody().message());
    }
}

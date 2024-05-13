package com.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.reservation.service.exception.InvalidReservationIdException;
import com.reservation.service.global.GlobalExceptionHandler;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {
 
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
 
    @Test
    void testHandleAllExceptions() {
        // Create a generic exception
        Exception exception = new Exception("Generic Exception Message");
 
        // Call the handler
        ResponseEntity<?> response = globalExceptionHandler.handleAllExceptions(exception);
 
        // Verify the response
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Generic Exception Message", response.getBody());
    }
 
    @Test
    void testHandleNotFoundException() {
        // Create an InvalidReservationIdException
        InvalidReservationIdException notFoundException = new InvalidReservationIdException("Invalid reservation ID");
 
        // Call the handler
        ResponseEntity<?> response = globalExceptionHandler.handleNotFoundException(notFoundException);
 
        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Invalid reservation ID", response.getBody());
    }
}



package com.room.microservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.room.microservice.exception.RoomNotAvailableException;
import com.room.microservice.exception.RoomNotFoundException;
import com.room.microservice.global.GlobalExceptionHandler;

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
    void testHandleRoomNotAvailableException() {
        // Create a RoomNotAvailableException
        RoomNotAvailableException notAvailableException = new RoomNotAvailableException("Room not available");
 
        // Call the handler
        ResponseEntity<?> response = globalExceptionHandler.handleNotFoundException(notAvailableException);
 
        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Room not available", response.getBody());
    }
 
    @Test
    void testHandleRoomNotFoundException() {
        // Create a RoomNotFoundException
        RoomNotFoundException notFoundException = new RoomNotFoundException("Room not found");
 
        // Call the handler
        ResponseEntity<?> response = globalExceptionHandler.handleNotFoundException(notFoundException);
 
        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Room not found", response.getBody());
    }

}

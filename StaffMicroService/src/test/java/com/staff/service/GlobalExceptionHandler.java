//package com.staff.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.staff.service.exception.StaffNotFoundException;
//
//@ExtendWith(MockitoExtension.class)
//public class GlobalExceptionHandler {
//	@InjectMocks
//    private GlobalExceptionHandler globalExceptionHandler;
// 
//    @Test
//    void testHandleAllExceptions() {
//        // Create a generic exception
//        Exception exception = new Exception("Generic Exception Message");
// 
//        // Call the handler
//        ResponseEntity<?> response = globalExceptionHandler.handleAllExceptions(exception);
// 
//        // Verify the response
//        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        assertEquals("Generic Exception Message", response.getBody());
//    }
// 
//    @Test
//    void testHandleStaffNotFoundException() {
//        // Create a StaffNotFoundException
//        StaffNotFoundException notFoundException = new StaffNotFoundException("Staff not found");
// 
//        // Call the handler
//        ResponseEntity<?> response = globalExceptionHandler.handleNotFoundException(notFoundException);
// 
//        // Verify the response
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals("Staff not found", response.getBody());
//    }
//}

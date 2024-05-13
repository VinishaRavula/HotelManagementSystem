package com.staff.service.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.staff.service.exception.StaffNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(Exception.class)
		public final ResponseEntity<?> handleAllExceptions(Exception ex) {

			return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		@ExceptionHandler(StaffNotFoundException.class)
		public final ResponseEntity<?> handleNotFoundException(StaffNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}

}

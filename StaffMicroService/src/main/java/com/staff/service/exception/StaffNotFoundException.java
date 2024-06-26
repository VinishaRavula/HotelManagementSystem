package com.staff.service.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StaffNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public StaffNotFoundException() {
	}

	public StaffNotFoundException(String message) {
		super(message);
	}
}


package com.casestudy.GuestMicroService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class GuestNotFoundException extends Exception {


	public GuestNotFoundException() {
	}

	public GuestNotFoundException(String message) {
		super(message);
	}
}
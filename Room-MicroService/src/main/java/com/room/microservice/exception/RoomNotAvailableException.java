package com.room.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomNotAvailableException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoomNotAvailableException() {
	}

	public RoomNotAvailableException(String message) {
		super(message);
	}
}
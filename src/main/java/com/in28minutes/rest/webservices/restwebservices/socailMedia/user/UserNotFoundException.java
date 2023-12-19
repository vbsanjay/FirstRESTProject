package com.in28minutes.rest.webservices.restwebservices.socailMedia.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND) // <- this indicates that the error code is 404
public class UserNotFoundException extends RuntimeException {
	private String message;
	
	public UserNotFoundException(String message) {
		super(message);
	}
}

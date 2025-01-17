package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

public class EmployeeNotFoundException extends RuntimeException {
	
	private final long serviceVersionId = 1L;
	
	public EmployeeNotFoundException(String message) {
		
		super(message);
	}

}

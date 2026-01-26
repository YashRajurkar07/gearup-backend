package com.gearup.customExceptions;

public class ResourceNotFoundException extends RuntimeException {


	public ResourceNotFoundException(String message) {
		super(message);
	
	}

}

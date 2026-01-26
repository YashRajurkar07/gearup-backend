package com.gearup.customExceptions;

public class InvalidDataException extends RuntimeException {

	public InvalidDataException(String message) {
		super(message);
	}
}

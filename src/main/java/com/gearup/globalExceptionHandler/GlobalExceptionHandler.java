package com.gearup.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gearup.customExceptions.ResourceAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
				
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleCheckedExceptions(Exception e){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleUncheckedExceptions(RuntimeException e){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}
}

package com.gearup.globalExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gearup.customExceptions.InvalidDataException;
import com.gearup.customExceptions.ResourceAlreadyExistsException;
import com.gearup.customExceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
				
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<?> handleInvalidDataException(InvalidDataException e){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleUncheckedExceptions(RuntimeException e){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		List<FieldError> fieldErrorsList = e.getFieldErrors();
		
		Map<String, String> errorMap = fieldErrorsList.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}
}

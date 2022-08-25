package com.books.stock.exchange.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.books.stock.exchange.exception.ExceptionDetails;
import com.books.stock.exchange.exception.ResourceAlreadyExistException;
import com.books.stock.exchange.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidException(MethodArgumentNotValidException exception) {
		Map<String, String> mapError = new HashMap<>();
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			mapError.put(error.getField(), error.getDefaultMessage());
		});
		return mapError;
	}

	/*
	 * @ResponseStatus(HttpStatus.NOT_FOUND)
	 * 
	 * @ExceptionHandler(ResourceNotFoundException.class) public Map<String, String>
	 * handleResourceNotFoundException(ResourceNotFoundException exception) {
	 * Map<String, String> errorMap = new HashMap<>(); errorMap.put("errorMessage",
	 * exception.getMessage()); return errorMap; }
	 */

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.NOT_FOUND, exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, exceptionDetails.getStatusCode());
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ExceptionDetails> handleResourceAlreadyExistException(ResourceAlreadyExistException exception,
			WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.CONFLICT, exception.getLocalizedMessage());
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, exceptionDetails.getStatusCode());
	}

}

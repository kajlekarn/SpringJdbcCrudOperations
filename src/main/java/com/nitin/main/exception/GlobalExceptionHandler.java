package com.nitin.main.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ExceptionResponse> insuffecientBalanceExceptionHandler(InsufficientBalanceException ex,
			WebRequest request) {
		ExceptionResponse response = prepareExceptionResponse(ex.getMessage(), request);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ExceptionResponse> customExceptionHandler(CustomException ex, WebRequest request,
			HttpStatus httpStatus) {
		ExceptionResponse response = prepareExceptionResponse(ex.getMessage(), request);
		return ResponseEntity.status(httpStatus).body(response);
	}

	private ExceptionResponse prepareExceptionResponse(String message, WebRequest request) {
		return new ExceptionResponse(LocalDateTime.now(), message, request.getDescription(false));
	}
}

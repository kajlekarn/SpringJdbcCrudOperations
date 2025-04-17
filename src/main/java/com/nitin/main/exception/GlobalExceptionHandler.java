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
		ExceptionResponse response = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}
}

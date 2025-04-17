package com.nitin.main.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
		super("Insufficient Funds!");
	}

	public InsufficientBalanceException(String message) {
		super(message);
	}
}

package com.nitin.main.service;

import com.nitin.main.payload.TransactionDTO;

public interface TransactionService {
	TransactionDTO withdraw(Long accountNumber, Double amount);

	TransactionDTO checkBalance(Long accountNumber);

	void updateBalance(Long accountNumber, Double amount);
}

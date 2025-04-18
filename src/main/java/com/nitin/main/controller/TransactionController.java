package com.nitin.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.main.payload.TransactionDTO;
import com.nitin.main.payload.WithdrawalTransactionRecord;
import com.nitin.main.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	private final TransactionService transactionService;

	TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping("/check-balance/{account-number}")
	public ResponseEntity<TransactionDTO> checkAccountBalance(@PathVariable("account-number") Long accountNumber) {
		LOGGER.info("Fetching Account Balance for A/C No: {}", accountNumber);
		return ResponseEntity.ok(transactionService.checkBalance(accountNumber));
	}

	@PostMapping("/withdraw")
	public ResponseEntity<TransactionDTO> withdraw(@RequestBody WithdrawalTransactionRecord withDrawalTransaction) {
		LOGGER.info("Withdrawing Acoumnt {} from A/C No: {}", withDrawalTransaction.accountNumber(),
				withDrawalTransaction.amount());
		return ResponseEntity
				.ok(transactionService.withdraw(withDrawalTransaction.accountNumber(), withDrawalTransaction.amount()));
	}

}

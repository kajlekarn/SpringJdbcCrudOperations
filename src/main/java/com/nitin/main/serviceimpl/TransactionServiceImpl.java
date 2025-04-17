package com.nitin.main.serviceimpl;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.nitin.main.entity.Account;
import com.nitin.main.entity.Customer;
import com.nitin.main.exception.InsufficientBalanceException;
import com.nitin.main.payload.TransactionDTO;
import com.nitin.main.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private final JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	public TransactionServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public TransactionDTO withdraw(Long accountNumber, Double amount) {
		String accountQuery = "SELECT * FROM Account WHERE accountNumber = ?";

		@SuppressWarnings("deprecation")
		Account account = jdbcTemplate.queryForObject(accountQuery, new Object[] { accountNumber },
				(ResultSet rs, int rowNum) -> {
					Account a = new Account();
					a.setAccountNumber(rs.getLong("accountNumber"));
					a.setAccountBalance(rs.getDouble("accountBalance"));
					a.setCustomerId(rs.getLong("customerId"));
					return a;
				});

		if (account == null) {
			throw new IllegalArgumentException("Oops! Account not found!");
		} else if (account.getAccountBalance() < 0 || account.getAccountBalance() < amount) {
			throw new InsufficientBalanceException();
		}

		String customerQuery = "SELECT * FROM Customer WHERE customerId = ?";

		@SuppressWarnings("deprecation")
		Customer customer = jdbcTemplate.queryForObject(customerQuery, new Object[] { account.getCustomerId() },
				(ResultSet rs, int rowNum) -> {
					Customer c = new Customer();
					c.setCustomerId(rs.getLong("customerId"));
					c.setCustomerName(rs.getString("customerName"));
					c.setAccountNumber(rs.getLong("accountNumber"));
					return c;
				});

		if (customer == null) {
			throw new IllegalArgumentException("Oop! Unable to fetch customer data...");
		}

		Double updatedBalance = account.getAccountBalance() - amount;

		updateBalance(account.getAccountNumber(), updatedBalance);

		TransactionDTO transactionDto = checkBalance(account.getAccountNumber());
		String responseMessage = "Amount Withdrawn: " + amount;
		transactionDto.setResponseMessage(responseMessage);

		return transactionDto;
	}

	@Override
	public TransactionDTO checkBalance(Long accountNumber) {
		String query = "SELECT * FROM Account WHERE accountNumber = ?";
		@SuppressWarnings("deprecation")
		Account account = jdbcTemplate.queryForObject(query, new Object[] { accountNumber },
				(ResultSet rs, int rowNum) -> {
					Account a = new Account();
					a.setAccountId(rs.getLong("accountId"));
					a.setAccountNumber(rs.getLong("accountNumber"));
					a.setAccountBalance(rs.getDouble("accountBalance"));
					a.setCustomerId(rs.getLong("customerId"));
					return a;
				});

		if (account == null) {
			throw new IllegalArgumentException("Oops! Account not found!");
		}

		String customerQuery = "SELECT * FROM Customer WHERE customerId = ?";

		@SuppressWarnings("deprecation")
		Customer customer = jdbcTemplate.queryForObject(customerQuery, new Object[] { account.getCustomerId() },
				(ResultSet rs, int rowNum) -> {
					Customer c = new Customer();
					c.setCustomerId(rs.getLong("customerId"));
					c.setCustomerName(rs.getString("customerName"));
					c.setAccountNumber(rs.getLong("accountNumber"));
					return c;
				});

		if (customer == null) {
			throw new IllegalArgumentException("Oop! Unable to fetch customer data...");
		}

		TransactionDTO transactionDto = new TransactionDTO();
		transactionDto.setAccount(account);
		transactionDto.setCustomer(customer);
		String responseMessage = "Balance: " + account.getAccountBalance();
		transactionDto.setResponseMessage(responseMessage);

		return transactionDto;
	}

	@Override
	public void updateBalance(Long accountNumber, Double updatedBalance) {
		String updateBalanceQuery = "UPDATE Account SET accountBalance = ? WHERE accountNumber = ?";
		int rowsUpdated = jdbcTemplate.update(updateBalanceQuery, updatedBalance, accountNumber);
		if (rowsUpdated == 0) {
			LOGGER.warn("No account was updated for accountNumber: {}", accountNumber);
		} else {
			LOGGER.info("Account balance updated successfully for accountNumber: {}", accountNumber);
		}
	}

}

package com.nitin.main.payload;

import com.nitin.main.entity.Account;
import com.nitin.main.entity.Customer;

public class TransactionDTO {
	private String responseMessage;
	private Customer customer;
	private Account account;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "TransactionDTO [responseMessage=" + responseMessage + ", customer=" + customer + ", account=" + account
				+ "]";
	}

}

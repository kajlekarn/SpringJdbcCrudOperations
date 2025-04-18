package com.nitin.main.payload;

public class CustomerDTO {
	private Long customerId;
	private String customerName;
	private Long accountNumber;
	private Long accountId;
	private Double accountBalance;
	private Double accountOpeningBalance;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Double getAccountOpeningBalance() {
		return accountOpeningBalance;
	}

	public void setAccountOpeningBalance(Double accountOpeningBalance) {
		this.accountOpeningBalance = accountOpeningBalance;
	}

	@Override
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", customerName=" + customerName + ", accountNumber="
				+ accountNumber + ", accountId=" + accountId + ", accountBalance=" + accountBalance
				+ ", accountOpeningBalance=" + accountOpeningBalance + "]";
	}

}

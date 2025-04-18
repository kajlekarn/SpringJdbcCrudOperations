package com.nitin.main.mapper;

import org.springframework.stereotype.Component;

import com.nitin.main.entity.Customer;
import com.nitin.main.payload.CustomerDTO;

@Component
public class DtoToEntity {

	public Customer dtoToCustomerMapper(CustomerDTO customerDto) {
		Customer customer = new Customer();
		customer.setCustomerId(customerDto.getCustomerId() != null ? customerDto.getCustomerId() : null);
		customer.setCustomerName(customerDto.getCustomerName() != null ? customerDto.getCustomerName() : "");
		customer.setAccountNumber(customerDto.getAccountNumber() != null ? customerDto.getAccountNumber() : null);
		return customer;
	}

	public CustomerDTO customerToCustomerDto(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setCustomerId(customer.getCustomerId() != null ? customer.getCustomerId() : null);
		customerDto.setAccountNumber(customer.getAccountNumber() != null ? customer.getAccountNumber() : null);
		return customerDto;
	}
}

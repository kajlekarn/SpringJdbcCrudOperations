package com.nitin.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.main.payload.CustomerDTO;
import com.nitin.main.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	private final CustomerService customerService;

	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/create")
	public void createCustomer(@RequestBody CustomerDTO customerDto) {
		LOGGER.info("Received Customer Data: {}", customerDto);
		customerService.createCustomer(customerDto);
	}
}

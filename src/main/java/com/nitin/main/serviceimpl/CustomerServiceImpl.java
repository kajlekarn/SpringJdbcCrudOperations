package com.nitin.main.serviceimpl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.nitin.main.entity.Account;
import com.nitin.main.entity.Customer;
import com.nitin.main.mapper.DtoToEntity;
import com.nitin.main.payload.CustomerDTO;
import com.nitin.main.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private final JdbcTemplate jdbcTemplate;

	private final DtoToEntity dtoToEntity;

	CustomerServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate,
			DtoToEntity dtoToEntity) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.jdbcTemplate = jdbcTemplate;
		this.dtoToEntity = dtoToEntity;
	}

	@Override
	public void createCustomer(CustomerDTO customerDto) {
		Customer customer = dtoToEntity.dtoToCustomerMapper(customerDto);
		Customer lastSavedCx = getLastSavedCustomer();
		if (lastSavedCx != null) {
			customer.setCustomerId(lastSavedCx.getCustomerId() + 1);
			customer.setAccountNumber(lastSavedCx.getAccountNumber() + 1);
			String query = "INSERT INTO Customer VALUES (:customerId, :customerName, :accountNumber)";
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("customerId", customer.getCustomerId());
			dataMap.put("customerName", customer.getCustomerName());
			dataMap.put("accountNumber", customer.getAccountNumber());
			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setAccountNumber(customer.getAccountNumber());
			Account createdAccount = createCustomerAccount(customerDto);
			int count = namedParameterJdbcTemplate.update(query, dataMap);
			if (count > 0 && createdAccount != null) {
				LOGGER.info("Customer data saved successfully!");
			}
		}
	}

	private Customer getLastSavedCustomer() {
		String query = "SELECT * FROM Customer ORDER BY customerId DESC LIMIT 1";
		return jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
			Customer c = new Customer();
			c.setCustomerId(rs.getLong("customerId"));
			c.setCustomerName(rs.getString("customerName"));
			c.setAccountNumber(rs.getLong("accountNumber"));
			return c;
		});
	}

	private Account createCustomerAccount(CustomerDTO customerDto) {
		Account lastSavedAcc = getLastSavedAccount();
		if (lastSavedAcc != null) {
			String query = "INSERT INTO Account VALUES (:accountId, :accountNumber, :accountBalance, :customerId)";
			Map<String, Object> map = new HashMap<>();
			long newAccountId = lastSavedAcc.getAccountId() + 1;
			long newAccountNumber = lastSavedAcc.getAccountNumber() + 1;
			map.put("accountId", newAccountId);
			map.put("accountNumber", newAccountNumber);
			map.put("accountBalance", customerDto.getAccountOpeningBalance());
			map.put("customerId", customerDto.getCustomerId());
			namedParameterJdbcTemplate.update(query, map);
			LOGGER.info("Customer {} Account Create successfully!", customerDto.getCustomerId());
			Account account = new Account();
			account.setAccountId(newAccountId);
			account.setAccountNumber(newAccountNumber);
			account.setAccountBalance(customerDto.getAccountOpeningBalance());
			account.setCustomerId(customerDto.getCustomerId());

			return account;
		}

		return null;

	}

	private Account getLastSavedAccount() {
		String query = "SELECT * FROM Account ORDER BY accountId DESC LIMIT 1";
		return jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
			Account a = new Account();
			a.setAccountId(rs.getLong("accountId"));
			a.setAccountNumber(rs.getLong("accountNumber"));
			a.setAccountBalance(rs.getDouble("accountBalance"));
			return a;
		});
	}
}

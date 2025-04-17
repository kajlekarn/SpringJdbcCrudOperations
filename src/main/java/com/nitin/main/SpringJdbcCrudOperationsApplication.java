package com.nitin.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJdbcCrudOperationsApplication implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringJdbcCrudOperationsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcCrudOperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("------ SpringJdbcCrudOperationsApplication is UP! ACCEPTING TRAFFIC NOW ------");
	}

}

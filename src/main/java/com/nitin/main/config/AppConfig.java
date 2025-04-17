package com.nitin.main.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class AppConfig {

	@Value("${datasource.driver-class-name}")
	private String driverClassName;

	@Value("${datasource.url}")
	private String url;

	@Value("${datasource.username}")
	private String username;

	@Value("${datasource.password}")
	private String password;

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

	@Bean
	DriverManagerDataSource getDriverManagerDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		LOGGER.info("Driver Class Name: {}", driverClassName);
		LOGGER.info("URL: {}", url);
		LOGGER.info("Username: {}", username);
		LOGGER.info("Password: {}", password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDriverManagerDataSource());
	}
}

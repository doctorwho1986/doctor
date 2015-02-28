package com.doctor.spring.annotation.propertySource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceAnnotationBean {

	@Value("${jdbc.url}")
	private String jdbcUrl;

	@Value("${jdbc.username}")
	private String jdbcUsername;

	@Value("${jdbc.password}")
	private String jdbcPassword;

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}
	
	
}

package com.doctor.spring.annotation.propertySource2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourcesAnnotationBean {

	@Value("${jdbc.greenplum.url}")
	private String jdbcUrl;

	@Value("${jdbc.greenplum.username}")
	private String jdbcUsername;

	@Value("${jdbc.greenplum.password}")
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

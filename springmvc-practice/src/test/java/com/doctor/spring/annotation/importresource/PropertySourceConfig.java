package com.doctor.spring.annotation.importresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author doctor
 *
 * @since 2015年1月1日 下午4:30:39
 */
@Configuration
@PropertySource({ "classpath:/spring-annotation/jdbc.property" })
public class PropertySourceConfig {
	
	@Autowired
	private Environment environment;
	
	 
	private String url;

	private String username;

	 
	private String password;

	public String getUrl() {
		if (url == null) {
			url = environment.getProperty("jdbc.url");
		}
		return url;
	}

	public String getUsername() {
		if (username == null) {
			username = environment.getProperty("jdbc.username");
		}
		return username;
	}

	public String getPassword() {
		if (password == null) {
			password = environment.getProperty("jdbc.password");
		}
		return password;
	}

}

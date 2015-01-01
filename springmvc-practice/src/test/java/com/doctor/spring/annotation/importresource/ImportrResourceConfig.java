package com.doctor.spring.annotation.importresource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author doctor
 *
 * @since 2015年1月1日 下午4:04:43
 */
@Configuration
@ImportResource({ "classpath:/spring-annotation/properties-config.xml" })
public class ImportrResourceConfig {
	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}

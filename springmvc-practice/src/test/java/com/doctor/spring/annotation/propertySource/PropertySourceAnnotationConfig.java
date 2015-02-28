package com.doctor.spring.annotation.propertySource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/spring-annotation/jdbc.property")
@ComponentScan(basePackages={"com.doctor.spring.annotation.propertySource"})
public class PropertySourceAnnotationConfig {


	/**
	 * To resolve ${} in @Values, you must register a static PropertySourcesPlaceholderConfigurer in
	 * either XML or annotation configuration file.
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}

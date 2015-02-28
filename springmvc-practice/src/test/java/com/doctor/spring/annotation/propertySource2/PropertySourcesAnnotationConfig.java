package com.doctor.spring.annotation.propertySource2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @see http://www.mkyong.com/spring/spring-propertysources-example/
 * 
 *      Note If a property key is duplicated, the last declared file will ‘win’ and override.
 * 
 * 
 *      In Spring 4, you can use ignoreResourceNotFound to ignore the not found properties file
 *      配置文件线上用到
 * 
 *      设置app.home 系统属性，实现线上线下分开配置
 * @PropertySource("file:${app.home /app.properties")
 *                                  System.setProperty("app.home", "test");
 * 
 *                                  java -jar -Dapp.home="/home/doctor/test" example.jar
 * 
 * @author doctor
 *
 * @time 2015年2月28日 下午5:42:04
 */
@Configuration
@ComponentScan(basePackages = { "com.doctor.spring.annotation.propertySource2" })
@PropertySource(value = "classpath:/propertySourcesAnnotationPractice/jdbcs.property", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:/propertySourcesAnnotationPractice/jdbc-greenplum.properties")
public class PropertySourcesAnnotationConfig {

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

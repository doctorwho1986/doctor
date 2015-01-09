package com.doctor.springframework.web.view.messageconverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.doctor.springframework.web.view.messageconverter")
@ImportResource("classpath:/httpMessageConverterPractice/spring-context1.xml")
public class HttpMessageConverterPracticeConfig {

//	@Bean
//	public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter(){
//		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//		messageConverters.add(new MappingJackson2HttpMessageConverter());
//		messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
//		requestMappingHandlerAdapter.setMessageConverters(messageConverters );
//		return requestMappingHandlerAdapter;
//	}
}

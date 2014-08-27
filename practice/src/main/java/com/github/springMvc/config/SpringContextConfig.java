package com.github.springMvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.github.springMvc.example.service.impl","com.github.springMvc.example.domain"})
public class SpringContextConfig {
	
}

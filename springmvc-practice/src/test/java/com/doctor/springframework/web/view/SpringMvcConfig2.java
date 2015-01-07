package com.doctor.springframework.web.view;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages={"com.doctor.springframework.web.view"})
@ImportResource({"classpath:/contentNegotiatingViewResolverPractice/spring-mvc-config2.xml"})
public class SpringMvcConfig2 {
	
}

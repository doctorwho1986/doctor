package com.doctor.spring4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.doctor.spring4.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

//	@Bean
//	public ViewResolver viewResolver() {
//		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
//		internalResourceViewResolver.setSuffix(".jsp");
//		return internalResourceViewResolver;
//	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();// Configure static content handling
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 等价xml配置： <mvc:view-controller path="/" view-name="home"/>
		registry.addViewController("/").setViewName("index");
	}

	
	@Bean
	public TemplateResolver templateResolver(){
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(templateResolver);
		return springTemplateEngine;
	}
	
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		thymeleafViewResolver.setCharacterEncoding("utf-8");//解决页面中文乱码
		return thymeleafViewResolver;
	}
}

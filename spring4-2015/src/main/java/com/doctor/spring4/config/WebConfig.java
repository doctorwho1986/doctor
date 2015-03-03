package com.doctor.spring4.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.doctor.spring4.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Bean
	// public ViewResolver viewResolver() {
	// InternalResourceViewResolver internalResourceViewResolver = new
	// InternalResourceViewResolver();
	// internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
	// internalResourceViewResolver.setSuffix(".jsp");
	// return internalResourceViewResolver;
	// }

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();// Configure static content handling
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 等价xml配置： <mvc:view-controller path="/" view-name="home"/>
		registry.addViewController("/").setViewName("index");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FastJsonHttpMessageConverter());
	}

	@Bean
	public TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/html/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver(templateResolver);
		return springTemplateEngine;
	}

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine);
		thymeleafViewResolver.setCharacterEncoding("utf-8");// 解决页面中文乱码
		return thymeleafViewResolver;
	}

	@Bean
	public ContentNegotiationManager contentNegotiationManager() {
		ContentNegotiationManagerFactoryBean contentNegotiationManagerFactoryBean = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManagerFactoryBean.setFavorPathExtension(true);
		contentNegotiationManagerFactoryBean.setFavorParameter(false);

		contentNegotiationManagerFactoryBean.addMediaType("json", MediaType.APPLICATION_JSON);
		contentNegotiationManagerFactoryBean.addMediaType("html", MediaType.TEXT_HTML);

		contentNegotiationManagerFactoryBean.setDefaultContentType(MediaType.APPLICATION_JSON);
		return contentNegotiationManagerFactoryBean.getObject();
	}

	/**
	 * 根据ContentNegotiatingViewResolver选择视图后缀策略，不同路径展现不同视图，详见spring 文档。
	 * 
	 * @param contentNegotiationManager
	 * @param thymeleafViewResolver
	 * @return
	 */
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager, ThymeleafViewResolver thymeleafViewResolver) {
		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setContentNegotiationManager(contentNegotiationManager);
		contentNegotiatingViewResolver.setOrder(0);
		contentNegotiatingViewResolver.setViewResolvers(Arrays.asList(thymeleafViewResolver));

		contentNegotiatingViewResolver.setDefaultViews(Arrays.asList(new FastJsonJsonView()));
		return contentNegotiatingViewResolver;
	}
}

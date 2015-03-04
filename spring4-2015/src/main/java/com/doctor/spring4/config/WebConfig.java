package com.doctor.spring4.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
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

/**
 * 视图配置感觉xml配置要好，随时可动态改变，重启应用即可．
 * 
 * @author doctor
 *
 * @since 2015年3月5日 上午1:12:25
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.doctor.spring4.controller" })
@ImportResource({ "classpath:/spring4_2015Config/contentNegotiationManager-config.xml" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// @Bean
	// public ViewResolver viewResolver() {
	// InternalResourceViewResolver internalResourceViewResolver = new
	// InternalResourceViewResolver();
	// internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
	// internalResourceViewResolver.setSuffix(".jsp");
	// return internalResourceViewResolver;
	// }

	@Resource(name = "mediaTypes")
	private Properties mediaTypes;

	@Resource(name = "defaultContentType")
	private MediaType defaultContentType;

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

		// contentNegotiationManagerFactoryBean.addMediaType("json", MediaType.APPLICATION_JSON);
		// contentNegotiationManagerFactoryBean.addMediaType("html", MediaType.TEXT_HTML);
		// contentNegotiationManagerFactoryBean.setDefaultContentType(MediaType.APPLICATION_JSON);

		contentNegotiationManagerFactoryBean.setMediaTypes(mediaTypes);
		contentNegotiationManagerFactoryBean.setDefaultContentType(defaultContentType);

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

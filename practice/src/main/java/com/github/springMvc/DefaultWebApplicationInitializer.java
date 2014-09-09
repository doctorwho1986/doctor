package com.github.springMvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <web-app> <!-- Configure ContextLoaderListener to use
 * AnnotationConfigWebApplicationContext instead of the default
 * XmlWebApplicationContext --> <context-param>
 * <param-name>contextClass</param-name> <param-value>
 * org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * </param-value> </context-param>
 * 
 * <!-- Configuration locations must consist of one or more comma- or
 * space-delimited fully-qualified @Configuration classes. Fully-qualified
 * packages may also be specified for component-scanning --> <context-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>com.acme.AppConfig</param-value> </context-param>
 * 
 * <!-- Bootstrap the root application context as usual using
 * ContextLoaderListener --> <listener>
 * <listener-class>org.springframework.web.context
 * .ContextLoaderListener</listener-class> </listener>
 * 
 * <!-- Declare a Spring MVC DispatcherServlet as usual --> <servlet>
 * <servlet-name>dispatcher</servlet-name>
 * <servlet-class>org.springframework.web
 * .servlet.DispatcherServlet</servlet-class> <!-- Configure DispatcherServlet
 * to use AnnotationConfigWebApplicationContext instead of the default
 * XmlWebApplicationContext --> <init-param>
 * <param-name>contextClass</param-name> <param-value>
 * org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * </param-value> </init-param> <!-- Again, config locations must consist of one
 * or more comma- or space-delimited and fully-qualified @Configuration classes
 * --> <init-param> <param-name>contextConfigLocation</param-name>
 * <param-value>com.acme.web.MvcConfig</param-value> </init-param> </servlet>
 * 
 * <!-- map all requests for /app/* to the dispatcher servlet -->
 * <servlet-mapping> <servlet-name>dispatcher</servlet-name>
 * <url-pattern>/app/*</url-pattern> </servlet-mapping> </web-app>
 *
 */
public class DefaultWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(com.github.springMvc.config.SpringContextConfig.class);

		// <listener>
		// <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
		// </listener>
		servletContext.addListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		// hessian 要更改配置文件
		// dispatcherContext.register(com.github.springMvc.config.remoteServletConfig.class);
		dispatcherContext.register(com.github.springMvc.config.SpingMvcConfig.class);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("remote", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/remote/*");
	}

}

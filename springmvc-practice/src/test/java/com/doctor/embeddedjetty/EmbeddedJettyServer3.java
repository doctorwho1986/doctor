package com.doctor.embeddedjetty;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 标准spring 配置（java config） 嵌入式jetty9启动
 * @author doctor
 *
 * @time   2014年12月8日 下午4:07:40
 */
public class EmbeddedJettyServer3 {
	private int port ;
	private String contextPath;
	private Class<?> springRootConfiguration = null;
	private Class<?> springMvcConfiguration = null;
	
	private Server server;
	
	public EmbeddedJettyServer3(Class<?> springRootConfiguration,Class<?> springMvcConfiguration){
		this(8080,"/", springRootConfiguration,springMvcConfiguration);
	}
	public EmbeddedJettyServer3(int port,String contextPath,Class<?> springRootConfiguration,Class<?> springMvcConfiguration){
		this.port = port;
		this.contextPath = contextPath;
		this.springRootConfiguration = springRootConfiguration;
		this.springMvcConfiguration = springMvcConfiguration;
		init();
	}
	
	public void init(){
		server = new Server(port);
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath(contextPath);
		server.setHandler(context);
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(this.springRootConfiguration);
		context.addEventListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(springMvcConfiguration);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(mvcContext);
		context.addServlet(new ServletHolder(dispatcherServlet), "/*");

	}
	public void start() throws Exception{
		if (server!= null) {
			if (server.isStarting() || server.isStarted() || server.isRunning() ) {
				return;
			}
		}
		TimeUnit.SECONDS.sleep(3);
		server.start();
	}
	
	public void stop() throws Exception{
		if (server != null) {
			if (server.isRunning()) {
				server.stop();
			}
		}
	}
	
	public void join() throws InterruptedException{
		if (server!=null) {
			server.join();
		}
	}
}

package com.doctor.embeddedjetty;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class EmbeddedJettyServer {
	private int port ;
	private Class<?> springConfiguration = null;
	
	private Server server;
	
	public EmbeddedJettyServer(Class<?> springConfiguration){
		this(8080, springConfiguration);
	}
	public EmbeddedJettyServer(int port,Class<?> springConfiguration){
		this.port = port;
		this.springConfiguration = springConfiguration;
		init();
	}
	
	public void init(){
		server = new Server(port);
		ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		server.setHandler(context);
		
		AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
		webApplicationContext.register(springConfiguration);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
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

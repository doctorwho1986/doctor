package com.doctor.embeddedjetty;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 标准spring 配置（java config） 嵌入式jetty9启动,支持jsp试图，测试用例看
 * {@link ContentNegotiatingViewResolverPractice} ContentNegotiatingViewResolverPractice
 * @author doctor
 *
 * @since 2015年1月6日 下午10:40:16
 */
public class EmbeddedJettyServer3 {
	private int port;
	private String resourceBase;
	private Class<?> springRootConfiguration = null;
	private Class<?> springMvcConfiguration = null;

	private Server server;

	public EmbeddedJettyServer3(String resourceBase, Class<?> springRootConfiguration, Class<?> springMvcConfiguration) {
		this(8080, resourceBase, springRootConfiguration, springMvcConfiguration);
	}

	/**
	 * 
	 * @param port
	 * @param resourceBase 注：这里是相对路径，web　src/test/resources路径，绝对路径没判断
	 * @param springRootConfiguration
	 * @param springMvcConfiguration
	 */
	public EmbeddedJettyServer3(int port, String resourceBase, Class<?> springRootConfiguration, Class<?> springMvcConfiguration) {
		this.port = port;
		this.resourceBase = resourceBase;
		this.springRootConfiguration = springRootConfiguration;
		this.springMvcConfiguration = springMvcConfiguration;
		init();
	}

	public void init() {
		server = new Server(port);
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		try {
			context.setResourceBase(this.getClass().getResource(resourceBase).toURI().toASCIIString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.setHandler(context);

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(this.springRootConfiguration);
		context.addEventListener(new ContextLoaderListener(rootContext));

		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(springMvcConfiguration);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(mvcContext);
		context.addServlet(new ServletHolder(dispatcherServlet), "/");
//		context.addServlet(new ServletHolder(new JspServlet()), "*.jsp");
	}

	public void start() throws Exception {
		if (server != null) {
			if (server.isStarting() || server.isStarted() || server.isRunning()) {
				return;
			}
		}
		TimeUnit.SECONDS.sleep(3);
		server.start();
	}

	public void stop() throws Exception {
		if (server != null) {
			if (server.isRunning()) {
				server.stop();
			}
		}
	}

	public void join() throws InterruptedException {
		if (server != null) {
			server.join();
		}
	}
}

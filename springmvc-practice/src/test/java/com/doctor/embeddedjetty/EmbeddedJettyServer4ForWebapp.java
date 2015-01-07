package com.doctor.embeddedjetty;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 标准spring 配置（java config） 嵌入式jetty9启动,支持jsp试图,标准webapp目录，测试用例看
 * {@link EmbeddedJettyServer4ForWebappTest} EmbeddedJettyServer4ForWebappTest
 * @author doctor
 *
 * @since 2015年1月6日 下午10:40:16
 */
public class EmbeddedJettyServer4ForWebapp {
	private int port;
	private String resourceBase;

	private Server server;

	public EmbeddedJettyServer4ForWebapp(String resourceBase) {
		this(8080, resourceBase);
	}
	
	public EmbeddedJettyServer4ForWebapp(int port,String resourceBase) {
		this.resourceBase = resourceBase;
		this.port = port;
		init();
	}

	/**
	 * 
	 * @param port
	 * @param resourceBase 注：这里是相对路径，web　src/test/resources路径，绝对路径没判断
	 * @param springRootConfiguration
	 * @param springMvcConfiguration
	 */

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

package com.github.jetty;

import java.io.File;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbedJetty {
	private Server server = new Server();
	private String projectDir = new File("").getAbsolutePath();
	private Map<String, WebAppContext> contexts = new HashMap<String, WebAppContext>();

	public EmbedJetty(String contextPath, String webAppRelativePath) {
		this(8080, "localhost", contextPath, webAppRelativePath, null);
	}

	public EmbedJetty(int port, String hostName, String contextPath, String webAppRelativePath, String webAppParentPath) {
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(port);
		connector.setHost(hostName);
		server.addConnector(connector);
		if (!StringUtils.isBlank(webAppParentPath)) {
			projectDir = webAppParentPath;
		}
		addWebAppContext(contextPath, webAppRelativePath);
	}

	public void addWebAppContext(String contextPath, String webAppRelativePath) {
		addWebAppContext(contextPath, webAppRelativePath, new String[] {});
	}

	public void addWebAppContext(String contextPath, String webAppRelativePath, String... hostNames) {
		WebAppContext webapp = new WebAppContext(projectDir + webAppRelativePath, contextPath);
		if (0 != hostNames.length) {
			webapp.setVirtualHosts(hostNames);
		}
		contexts.put(contextPath, webapp);
		int sum = contexts.size();
		Handler[] handlers = new WebAppContext[sum];
		Collection<WebAppContext> values = contexts.values();
		int i = 0;
		for (WebAppContext webAppContext : values) {
			handlers[i++] = webAppContext;
		}
		ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
		contextHandlerCollection.setHandlers(handlers);
		server.setHandler(contextHandlerCollection);
	}

	public void addServlet(String contextPath, String urlPattern, Servlet servlet) {
		if (!contexts.containsKey(contextPath)) {
			throw new IllegalArgumentException(contextPath + " is not exist");
		}
		contexts.get(contextPath).addServlet(new ServletHolder(servlet), urlPattern);
	}

	public void addFilter(String contextPath, String urlPattern, Filter filter, DispatcherType... dispatcherType) {
		EnumSet<DispatcherType> dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
		for (DispatcherType t : dispatcherType) {
			dispatcherTypes.add(t);
		}
		contexts.get(contextPath).addFilter(new FilterHolder(filter), urlPattern, dispatcherTypes);
	}

	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			throw new RuntimeException("jetty server start error");
		}
	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			throw new RuntimeException("jetty server stop error");
		}
	}

	public void join() {
		try {
			server.join();
		} catch (InterruptedException e) {
			throw new RuntimeException("jetty server jion error");
		}
	}
}

package com.github.netexample.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrimitiveServlet implements Servlet {
	protected static final Logger logger = LoggerFactory.getLogger(PrimitiveServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("PrimitiveServlet init");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		logger.info("PrimitiveServlet service");
		PrintWriter printWriter = res.getWriter();
		printWriter.write("hello doctor");

	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
		logger.info("PrimitiveServlet destroy ");
	}

}

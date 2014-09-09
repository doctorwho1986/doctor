package com.github.springMvc.springbeans.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringFilterTemplate implements Filter {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(getClass().getName() + " Filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info(getClass().getName() + " Filter doFilter");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info(getClass().getName() + " Filter destroy");
	}

}

package com.gitjub.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PracticeFilter implements Filter{
	private static final Logger log = LoggerFactory.getLogger(PracticeFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{PracticeFilter :'init'}");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("{PracticeFilter :'doFilter'}");
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		log.info("{PracticeFilter :'destroy'}");
		
	}

}

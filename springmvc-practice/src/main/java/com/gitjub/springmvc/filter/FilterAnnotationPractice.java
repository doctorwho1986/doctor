package com.gitjub.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/*")
public class FilterAnnotationPractice implements Filter {
	private static final Logger log = LoggerFactory.getLogger(FilterAnnotationPractice.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{msg:'FilterAnnotationPractice  init'}");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info("{msg:'FilterAnnotationPractice  doFilter'}");
		
	}

	@Override
	public void destroy() {
		log.info("{msg:'FilterAnnotationPractice  destroy'}");
		
	}

}

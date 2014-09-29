package com.gitjub.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gitjub.springmvc.requestwapper.ServletRequestWrapperPractice;

@WebFilter("/escape.html")
public class RequestWrapperEscapeFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(RequestWrapperEscapeFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletRequestWrapperPractice httpServletRequest =   new ServletRequestWrapperPractice((HttpServletRequest)request);
		log.info("{escape filter doFilter ///}");
		chain.doFilter(httpServletRequest, response);
	}

	@Override
	public void destroy() {

	}

}

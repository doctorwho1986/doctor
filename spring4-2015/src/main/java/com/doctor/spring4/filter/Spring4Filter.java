package com.doctor.spring4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter 托管给spring 代理
 * 
 * @author doctor
 *
 * @time 2015年3月11日  
 */
public class Spring4Filter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(Spring4Filter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//被托管后的Filter，生命周期改变了,这个方法不会调用
		log.info(getClass()+ " init ");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info(getClass()+ " doFilter ");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		//被托管后的Filter，生命周期改变了,这个方法不会调用
		log.info(getClass()+ " destroy ");

	}

}

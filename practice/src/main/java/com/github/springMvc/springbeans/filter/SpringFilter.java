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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.springMvc.example.domain.Person;

@Component
public class SpringFilter implements Filter {
	protected final static Logger log = LoggerFactory.getLogger(SpringFilter.class);
	
	@Autowired
	@Qualifier("person")
	private Person person;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info(getClass().getName() + "Filter init");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info(getClass().getName() + "Filter doFilter");
		log.info("{person:{}}", person.getName());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info(getClass().getName() + "Filter destroy");

	}

}

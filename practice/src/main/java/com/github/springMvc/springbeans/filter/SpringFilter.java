package com.github.springMvc.springbeans.filter;

import java.io.IOException;
import java.util.List;

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
	
	@Autowired
	private List<SpringFilterTemplate> listFilter;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (null != listFilter) {
			for (SpringFilterTemplate filter : listFilter) {
				filter.init(filterConfig);
			}
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		log.info(getClass().getName() + "Filter doFilter");
		log.info("{person:{}}", person.getName());
//		chain.doFilter(request, response);
		if (null != listFilter) {
			for (SpringFilterTemplate filter : listFilter) {
				filter.doFilter(request, response, chain);
			}
		}
	}

	@Override
	public void destroy() {
		if (null != listFilter) {
			for (SpringFilterTemplate filter : listFilter) {
				filter.destroy();
			}
		}

	}

}

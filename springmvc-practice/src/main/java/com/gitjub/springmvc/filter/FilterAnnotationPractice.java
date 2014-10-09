package com.gitjub.springmvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		log.info("{ContextPath :'{}'}",httpRequest.getServletContext().getContextPath());
		log.info("{ServerInfo :'{}'}",httpRequest.getServletContext().getServerInfo());
		log.info("{RequestURL :'{}'}",httpRequest.getRequestURL());
		log.info("{RequestURI :'{}'}",httpRequest.getRequestURI());
		log.info("{PathInfo() :'{}'}",httpRequest.getPathInfo());
		log.info("{CharacterEncoding() :'{}'}",httpRequest.getCharacterEncoding());
		
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		Cookie cookie = new Cookie("doctorname", "doctor who");
		httpServletResponse.addCookie(cookie);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		log.info("{msg:'FilterAnnotationPractice  destroy'}");
		
	}

}

package com.gitjub.springmvc.requestwapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;

public class ServletRequestWrapperPractice extends HttpServletRequestWrapper {

	public ServletRequestWrapperPractice(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String value = getRequest().getParameter(name);
		System.out.println(value);
		return StringEscapeUtils.escapeHtml4(value);
	}
	
	

}

package com.gitjub.springmvc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Controller
public class ControllerPractice1 {
	private static final Logger log = LoggerFactory.getLogger(ControllerPractice1.class);

	@RequestMapping(value="/jsptest",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String jspTest() {
		return " ! hello jsp tester,\n time now:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	
	@RequestMapping(value="/jsp",method={RequestMethod.GET,RequestMethod.POST})
	public String jsp() {
		return "jsptest";
	}
	
	
	@RequestMapping(value="/jSPScriptlets",method={RequestMethod.GET})
	public String jSPScriptlets() {
		return "jSPScriptlets";
	}
	
	@RequestMapping(value="/form-post")
	@ResponseBody
	public String getEncode() {
		String message = "";
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		//在web.xml里面配置filter org.springframework.web.filter.CharacterEncodingFilter
		message = message + request.getCharacterEncoding();
		
		return message;
	}
	
	@RequestMapping(value="/escape",method=RequestMethod.POST)
	@ResponseBody
	public String getEscapeString(@RequestParam("name") String name) {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		log.info("{getEscapeString  {}: '{}'}",name,request.getParameter("name"));
		return request.getParameter("name");
	}
	
	@RequestMapping(value="/cookie",method=RequestMethod.GET)
	@ResponseBody
	public String getCookies(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("cookies:<br/>");
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		Cookie[] cookies = requestAttributes.getRequest().getCookies();
		if (null != cookies) {
			log.info("{cookie length:{}}",cookies.length);
			for (Cookie cookie : cookies) {
				stringBuffer.append(cookie.getName()).append(":").append(cookie.getValue());
				stringBuffer.append("<br/>");
			}
		}
		return stringBuffer.toString();
	}
}

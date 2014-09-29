package com.gitjub.springmvc.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
public class ControllerPractice1 {
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
}

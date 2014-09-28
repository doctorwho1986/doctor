package com.gitjub.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerPractice1 {
	@RequestMapping(value="/jsptest",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String jspTest() {
		return "hello jsp tester";
	}
}

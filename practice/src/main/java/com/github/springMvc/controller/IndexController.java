package com.github.springMvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@RequestMapping("/index.htm")
	@ResponseBody
	public String helloIndex(){
		return "hello doctor ";
	}
}

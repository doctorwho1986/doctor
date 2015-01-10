package com.doctor.springframework.web.view;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
@Controller
public class SimpleController {
	
	@RequestMapping({"/test.html","/test.json"})
	public ModelAndView test() {
		Map<String, String> map = new HashMap<>();
		map.put("test", "json");
		map.put("test-html", "html");
		map.put("what", "what");
		ModelAndView modelAndView = new ModelAndView("/contentViewTest");
		modelAndView.addObject(map);
		return modelAndView;
		
	}
	
	@RequestMapping(value="/getcontent.do",method=RequestMethod.GET)
	@ResponseBody
	public String get_Content(){
		return "getContent";
	}
	
}

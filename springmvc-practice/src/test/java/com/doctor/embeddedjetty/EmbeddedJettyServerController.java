package com.doctor.embeddedjetty;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmbeddedJettyServerController {

	@RequestMapping({"/jetty/test.html","/jetty/test.json"})
	public ModelAndView test2() {
		Map<String, String> map = new HashMap<>();
		map.put("test", "json");
		map.put("test-html", "html");
		map.put("what", "what");
		ModelAndView modelAndView = new ModelAndView("jettytest/test");
		modelAndView.addObject(map);
		return modelAndView;
		
	}
}

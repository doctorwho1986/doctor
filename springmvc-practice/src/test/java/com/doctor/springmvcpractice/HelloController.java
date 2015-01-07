package com.doctor.springmvcpractice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author doctor
 *
 * @since 2015年1月7日 下午11:05:24
 */
@Controller
public class HelloController {

	@RequestMapping(value={"/welcome.do"},method=RequestMethod.GET)
	public String printWelcome(ModelMap modelMap){
		modelMap.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
}

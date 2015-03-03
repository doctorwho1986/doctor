package com.doctor.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * rest 请求
 * @author doctor
 *
 * @time 2015年3月3日 下午3:12:27
 */

@Controller
@RequestMapping("/movie")
public class MyRestController {

	@RequestMapping("/a.json")
	public String getDefault(ModelMap modelMap) {
		modelMap.addAttribute("movieName", "doctor who");
		return "movie";
	}

	@RequestMapping("/{movieName}/a.json")
	public String getMovie(@PathVariable String movieName, ModelMap modelMap) {
		modelMap.addAttribute("movie", movieName);
		return "movie";
	}
}

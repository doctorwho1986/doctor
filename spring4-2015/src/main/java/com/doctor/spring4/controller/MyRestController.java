package com.doctor.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * rest 请求
 * 
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

	/**
	 * java反射不会得到方法参数@PathVariable　注解的此参数的名字(其实得到的是arg0,arg1,...)，spring如何做到的呢，详见：
	 * {@link org.springframework.core.LocalVariableTableParameterNameDiscoverer}
	 * 利用字节码技术得到的．mybatis注解的mapper是采用java 反射技术，多个参数必须给注解提供具体变量名值．
	 * 
	 * @param movieName
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/{movieName}/a.json")
	public String getMovie(@PathVariable String movieName, ModelMap modelMap) {
		modelMap.addAttribute("movie", movieName);
		return "movie";
	}
}

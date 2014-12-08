package com.doctor.embeddedjetty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SImpleController2 {
	
	@Autowired
	@Qualifier("simpleService")
	private SimpleService simpleService;
	
	@RequestMapping(value="/embeddedJettyServer2Test",method=RequestMethod.GET)
	@ResponseBody
	public String getMessage(){
		return simpleService.getMessage();
	}
}

package com.doctor.embeddedjetty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SImpleController2 {
	private static final Logger log = LoggerFactory.getLogger(SImpleController2.class);
	@Autowired
	@Qualifier("simpleService")
	private SimpleService simpleService;
	
	@RequestMapping(value="/embeddedJettyServer2Test",method=RequestMethod.GET)
	@ResponseBody
	public String getMessage(){
		log.info("{SImpleController2 :'getMessage'}");
		return simpleService.getMessage();
	}
}

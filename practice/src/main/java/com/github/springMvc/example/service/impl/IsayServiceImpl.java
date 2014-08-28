package com.github.springMvc.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.springMvc.example.domain.Person;
import com.github.springMvc.example.service.IsayService;

@Service("isayService")
public class IsayServiceImpl implements IsayService{
	private static Logger logger = LoggerFactory.getLogger(IsayServiceImpl.class);
	
	@Autowired
	@Qualifier("person")
	Person person;
	
	@Override
	public String sayHello(String message) {
		person.setName("sd");
		return "hello " + message;
	}

	@Override
	public Person getPerson() {
		person.setName("doctor");
		logger.info("{msg:get person name: {}}", person.getName());;
		return person;
	}
	
	

}

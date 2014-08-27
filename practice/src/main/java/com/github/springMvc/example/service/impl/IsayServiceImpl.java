package com.github.springMvc.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.springMvc.example.domain.Person;
import com.github.springMvc.example.service.IsayService;

@Service("isayService")
public class IsayServiceImpl implements IsayService{
	@Autowired
	@Qualifier("person")
	Person person;
	
	@Override
	public String sayHello(String message) {
		person.setName("sd");
		System.out.println(person.getName());
		return "hello " + message;
	}

}

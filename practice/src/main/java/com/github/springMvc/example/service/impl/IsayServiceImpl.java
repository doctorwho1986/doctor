package com.github.springMvc.example.service.impl;

import org.springframework.stereotype.Service;

import com.github.springMvc.example.service.IsayService;

@Service("isayService")
public class IsayServiceImpl implements IsayService{

	@Override
	public String sayHello(String message) {
		return "hello " + message;
	}

}

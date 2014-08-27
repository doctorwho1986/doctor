package com.github.springMvc.example.service.impl;

import com.github.springMvc.example.service.IsayService;

public class IsayServiceImpl implements IsayService{

	@Override
	public String sayHello(String message) {
		return "hello " + message;
	}

}

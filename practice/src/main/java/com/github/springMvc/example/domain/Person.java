package com.github.springMvc.example.domain;

import org.springframework.stereotype.Component;

@Component("person")
public class Person {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
}

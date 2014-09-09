package com.github.springMvc.example.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component("person")
public class Person implements Serializable {
	
	private static final long serialVersionUID = -4205400683861996647L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name =" hello doctor";
	
}

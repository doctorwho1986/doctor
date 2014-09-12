package com.github.springMvc.example.domain;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component("person")
public class Person implements Serializable {
	public enum Gender{
		MAN,WOMAN
	}
	private static final long serialVersionUID = -4205400683861996647L;
	private String name =" hello doctor";
	private Gender gender;
	private String address;

	public Person(String name,Gender gender,String address ){
		this.name = name;
		this.gender = gender;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Person: ").append("{");
		Field[] declaredFields = Person.class.getDeclaredFields();
		try {
			for (Field field : declaredFields) {
				stringBuffer.append(field.getName()).append(" = ").append(field.get(this)).append(", ");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
		return stringBuffer.toString();
	}
	
}


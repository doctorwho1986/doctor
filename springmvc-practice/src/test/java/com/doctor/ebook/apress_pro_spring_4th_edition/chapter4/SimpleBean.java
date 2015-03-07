package com.doctor.ebook.apress_pro_spring_4th_edition.chapter4;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * @author doctor
 *
 * @since 2015年3月7日 下午2:39:35
 */
@Component("simpleBean")
public class SimpleBean {
	private static final String defalutName = "doctor who";

	private String name;

	@Value("26")
	private int age = Integer.MIN_VALUE;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@PostConstruct
	public void init() {
		if (name == null) {
			name = defalutName;
		}
		if (age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("age is empty");
		}
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	
	@PreDestroy
	public void destroy(){
		System.out.println("----"+getClass()+ ":" + "  destroy ");
	}
}

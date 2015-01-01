package com.doctor.spring.annotation;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2015年1月1日 上午11:32:41
 */
@Component("bean")
public class Bean {
	private String name = "doctor"; // 这个必须有外界访问权限，get 方法最适合．public不太好
	private Integer age = 100;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@PostConstruct
	public void init(){
		this.age = 600;
	}
}

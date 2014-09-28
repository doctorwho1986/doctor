package com.gitjub.springmvc.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonVo {
	private String name = "name";
	private String address = "china";
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getInfo(){
		return "name :" + name + ", address :" + address;
	}
	
	private static String timeFormateNow(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 如何在jstl中调用静态方法的技巧
	 * @return
	 */
	public String getTimeNow() {
		return timeFormateNow();
	}
}

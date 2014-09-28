package com.gitjub.springmvc.vo;

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
}

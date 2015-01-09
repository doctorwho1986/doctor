package com.doctor.springframework.web.view.messageconverter;

public class PersonBean {
	private String name;
	private String sex;
	private String addres;

	public PersonBean() {

	}

	public PersonBean(String name, String sex, String addres) {
		this.name = name;
		this.sex = sex;
		this.addres = addres;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

}

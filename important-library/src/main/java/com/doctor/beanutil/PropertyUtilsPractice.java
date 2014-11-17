package com.doctor.beanutil;


import org.apache.commons.beanutils.PropertyUtils;

public class PropertyUtilsPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		Person person = new Person("nam","address",125);
		PropertyUtils.describe(person).forEach((k,v) -> System.out.println(k+":" + v + "  "));

	}

	public static class Person{
		private String name;
		private String address;
		private Integer age;
		
		public Person(){}
		public Person(String name,String address,Integer age){
			this.name = name;
			this.address = address;
			this.age = age;
		}
		
		
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
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
	}
}

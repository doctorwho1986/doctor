package com.doctor.beanutil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;

import com.google.common.base.MoreObjects;

public class BeanUtilPractice {

	public static void main(String[] args) throws ReflectiveOperationException{
		Object cloneBean = BeanUtils.cloneBean(new Person("doctor", "where", 560));
		System.out.println(cloneBean);
		
		
		Map<String, String> map = new HashMap<>();
		map.put("name", "doctor");
		map.put("address", "wherewhere");
		map.put("age", "560");
		map.put("no", "no");
		Object cloneBean2 = BeanUtils.cloneBean(map);
		System.out.println();
		System.out.println(cloneBean2);
		
		
		Person person = new Person();
		System.out.println();
		System.out.println(person);
		BeanUtils.copyProperty(person, "name", "who");
		System.out.println(person);
		
		Person person2 = new Person("name-doctor", "unknown", 3467);
		Person person3 = new Person();
		System.out.println();
		System.out.println("person2 :" + person2);
		System.out.println("person3 :" + person3);
		BeanUtils.copyProperties(person3, person2);
		System.out.println("person3 :" + person3);
	
		Person person4 = new Person();
		System.out.println("person4:" + person4);
		BeanUtils.copyProperties(person4, map);
		System.out.println("person4:" + person4);
		
		PropertyUtils.clearDescriptors();
		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
		Map<String, String> describe = BeanUtils.describe(person4);
		System.out.println("describe person4:");
		describe.forEach((k,v) -> System.out.println(k+":" + v));
		
		Person person5 = new Person();
		System.out.println();
		System.out.println("person5:");
		BeanUtils.populate(person5, map);
		System.out.println(person5);
	}
	
	public static class Person{
		private String name;
		private String address;
		private Integer age;
		
		public Person() {
		}
		
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

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this).omitNullValues()
					.add("name", getName())
					.add("address", getAddress())
					.add("age", getAge())
					.toString();
		}
		
		
	}

}

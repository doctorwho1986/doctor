package com.doctor.beanutil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;

import com.google.common.base.MoreObjects;

public class SuppressPropertiesBeanIntrospectorPractice2 {
	static{
		//把ｃｌａｓｓ属性去掉
		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
	}

	public static void main(String[] args)throws ReflectiveOperationException {
		Person person = new Person();
		person.setAddress("unknown");
		person.setAge(2000);
		person.setName("doctor who");
		System.out.println(person);
		
		System.out.println();
		BeanUtils.describe(person).forEach((k,v) -> System.out.print(k+":" + v + "   "));

	}
	
	public static class Person{
		private String name;
		private Integer age;
		private String address;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		@Override
		public String toString() {
			
			return MoreObjects.toStringHelper(this).add("name", getName())
					.add("age", getAge())
					.add("address", getAddress())
					.toString();
		}
		
		
	}

}

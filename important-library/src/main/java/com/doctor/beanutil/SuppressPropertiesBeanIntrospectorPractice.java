package com.doctor.beanutil;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.beanutils.DefaultBeanIntrospector;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

public class SuppressPropertiesBeanIntrospectorPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		Person person = new Person();
		person.setAddress("unknown");
		person.setAge(2000);
		person.setBirth(LocalDateTime.of(1987, 12, 1, 0, 0));
		person.setName("doctor who");
		System.out.println(person);
		
		//常规自省　有class属性
		PropertyUtils.describe(person).forEach((k,v) -> System.out.print(k+":" + v + "   "));
		
		Preconditions.checkArgument(PropertyUtils.describe(person).containsKey("class"));
		
		//SuppressPropertiesBeanIntrospector去除class属性 不起作用？？
		PropertyUtils.removeBeanIntrospector(DefaultBeanIntrospector.INSTANCE);
		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
		System.out.println();
		PropertyUtils.describe(person).forEach((k,v) -> System.out.print(k+":" + v + "   "));
		System.out.println();
		System.out.println(PropertyUtils.getProperty(person, "class"));
		
		// 只能这样了
		System.out.println();
		Map<String, Object> describe = PropertyUtils.describe(person);
		describe.remove("class");
		describe.forEach((k,v) -> System.out.print(k+":" + v + "   "));
		
	}
	
	public static class Person{
		private String name;
		private Integer age;
		private LocalDateTime birth;
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
		public LocalDateTime getBirth() {
			return birth;
		}
		public void setBirth(LocalDateTime birth) {
			this.birth = birth;
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
					.add("birth", getBirth())
					.add("address", getAddress())
					.toString();
		}
		
		
	}

}

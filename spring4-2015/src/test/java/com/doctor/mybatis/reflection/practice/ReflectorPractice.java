package com.doctor.mybatis.reflection.practice;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.junit.Test;

/**
 * {@code Reflector} 　缓存了反射得到的属性和方法调用信息．
 * 
 * This class represents a cached set of class definition information that
 * allows for easy mapping between property names and getter/setter methods.
 * 
 * {@code Invoker}的子类封装了属性（get,set属性）和方法的一致调用方式．
 * 
 * 方法的签名：返回类型：方法名，参数类型名，参数类型名，．．．．，放到map里面，对于重载的set方法，
 * java反射只能得到一个方法．
 * 
 * @author doctor　
 *
 * @time 2015年3月12日 　
 */
public class ReflectorPractice {

	private Address address = new Address(1L, "山东", "888");
	private Person person = new Person(2L, "doctor", "man", address);

	private Reflector reflectorAddress = Reflector.forClass(Address.class);
	private Reflector reflectorPerson = Reflector.forClass(Person.class);

	@Test
	public void test_findPropertyName() {

		assertThat(reflectorAddress.findPropertyName("id"), equalTo("id"));

		assertThat(reflectorPerson.findPropertyName("address"), equalTo("address"));
		assertThat(reflectorPerson.findPropertyName("city"), equalTo(null));

	}

	@Test
	public void test_getGetInvoker() {
		Invoker getInvoker = reflectorAddress.getGetInvoker("city");
		System.out.println(getInvoker);
		
		getInvoker = reflectorPerson.getGetInvoker("address");
		System.out.println(getInvoker);
	}

	public static class Person {
		private Long id;
		private String name;
		private String sex;
		private Address address;

		public Person() {

		}

		public Person(Long id, String name, String sex, Address address) {
			this.id = id;
			this.name = name;
			this.sex = sex;
			this.address = address;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}
		
		public void setAddress(Long id) {
			this.address = new Address(id, null, null);
		}

	}

	public static class Address {
		private Long id;
		private String city;
		private String no;

		public Address() {

		}

		public Address(Long id, String city, String no) {
			this.id = id;
			this.city = city;
			this.no = no;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getNo() {
			return no;
		}

		public void setNo(String no) {
			this.no = no;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

	}
}

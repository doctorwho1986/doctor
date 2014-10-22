package com.github.springcorepractice;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.List;

import org.springframework.beans.ExtendedBeanInfoFactory;
import org.yaml.snakeyaml.Yaml;


public class ExtendedBeanInfoFactoryPractice {

	public static void main(String[] args) throws IntrospectionException {
		ExtendedBeanInfoFactory extendedBeanInfoFactory = new ExtendedBeanInfoFactory();
		BeanInfo beanInfo = extendedBeanInfoFactory.getBeanInfo(PessonBean.class);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
		
		System.out.println(new Yaml().dump(methodDescriptors));
		System.out.println(new Yaml().dump(propertyDescriptors));
		
		for (PropertyDescriptor pd : propertyDescriptors) {
			if (pd.getName().equals("class")) {
				return;
			}
			System.out.println(pd.getName() + " read method :" + pd.getReadMethod().getName() + " write method: " + pd.getWriteMethod().getName());
			
		}

	}

	public static class PessonBean{
		private String name;
		private int age;
		private List<String> address;
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
		public List<String> getAddress() {
			return address;
		}
		public PessonBean setAddress(List<String> address) {
			this.address = address;
			return this;
		}
		
		
	}
}

package com.doctor.spring.context.inject;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author doctor
 *
 * @since 2015年1月7日 下午9:05:50
 */
public class PropertyPlaceholderConfigurerPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/injectValueIntoBeanPropertiesPractice/propertyPlaceholderConfigurerPractice.xml");
		PropertyPlaceholderConfigurerBean placeholderConfigurerBean = context.getBean(PropertyPlaceholderConfigurerBean.class);
		System.out.println(placeholderConfigurerBean.getDriverClassName());
		System.out.println(placeholderConfigurerBean.getPassword());
		System.out.println(placeholderConfigurerBean.getUrl());
		System.out.println(placeholderConfigurerBean.getUsername());
		
		System.out.println("/////////////////////////");
		
		
		context = new ClassPathXmlApplicationContext("classpath:/injectValueIntoBeanPropertiesPractice/propertyPlaceholderConfigurerPractice2.xml");
		placeholderConfigurerBean = context.getBean(PropertyPlaceholderConfigurerBean.class);
		System.out.println(placeholderConfigurerBean.getDriverClassName());
		System.out.println(placeholderConfigurerBean.getPassword());
		System.out.println(placeholderConfigurerBean.getUrl());
		System.out.println(placeholderConfigurerBean.getUsername());
	}

}

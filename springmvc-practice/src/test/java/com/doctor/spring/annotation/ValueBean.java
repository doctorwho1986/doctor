package com.doctor.spring.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2015年1月1日 上午11:33:27
 */

@Component("valueBean")
public class ValueBean {

	@Value("#{bean.name}")
	private String beanName;
	
	@Value("#{bean.age}")
	private Integer beanAge;

	public String getBeanName() {
		return beanName;
	}

	public Integer getBeanAge() {
		return beanAge;
	}

}

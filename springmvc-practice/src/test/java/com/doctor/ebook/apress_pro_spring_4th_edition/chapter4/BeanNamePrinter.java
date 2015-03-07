package com.doctor.ebook.apress_pro_spring_4th_edition.chapter4;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2015年3月7日 下午3:21:48
 */
@Component("doctorBeanNamePrinter")
public class BeanNamePrinter implements BeanNameAware {
	private String beanName;
	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}
	public String getBeanName() {
		return beanName;
	}

}

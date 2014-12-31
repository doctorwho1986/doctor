package com.doctor.spring.context.annotationbasedconfiguration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author doctor
 *
 * @since 2015年1月1日 上午1:33:56
 */
@Component("anotherBean")
public class AnotherBean {

	@Autowired
	private BaseBean[] baseBeans;

	/**
	 * @return the baseBeans
	 */
	public BaseBean[] getBaseBeans() {
		return Arrays.copyOf(baseBeans, baseBeans.length);
	}
	
	
}

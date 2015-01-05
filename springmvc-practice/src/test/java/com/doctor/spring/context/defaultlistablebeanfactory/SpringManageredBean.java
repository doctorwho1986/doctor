package com.doctor.spring.context.defaultlistablebeanfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("springManageredBean")
public class SpringManageredBean {
	private static final Logger log = LoggerFactory.getLogger(SpringManageredBean.class);
	
	private SpringNotManageredBean springNotManageredBean;
	
	
	public void setSpringNotManageredBean(SpringNotManageredBean springNotManageredBean) {
		this.springNotManageredBean = springNotManageredBean;
	}


	public void checkSpringNotManageredBean() {
		log.info("{SpringManageredBean : {}}",springNotManageredBean.toString());
	}
}

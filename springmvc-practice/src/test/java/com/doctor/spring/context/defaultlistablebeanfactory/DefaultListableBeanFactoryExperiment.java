package com.doctor.spring.context.defaultlistablebeanfactory;

/**
 * 这种不太适合的bean 注入管理,依赖
 * @author doctor
 *
 * @time   2015年1月5日 下午5:33:22
 */
public class DefaultListableBeanFactoryExperiment {

	public static void main(String[] args) {
		SpringNotManageredBean springNotManageredBean = new SpringNotManageredBean();
		springNotManageredBean.init();
		springNotManageredBean.getContext().getBean(SpringManageredBean.class).checkSpringNotManageredBean();

	}

}

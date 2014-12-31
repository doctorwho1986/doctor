package com.doctor.spring.context.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 如果你想在Spring容器完成bean的实例化，配置和其他的初始化后执行一些自定义逻辑，
 * 我们可以通过插入一个或多个BeanPostProcessor实现.
 * 
 * BeanPostProcessor的作用域是容器级别的，它只对容器中的bean进行后置处理， 它不会对另一个容器中的bean进行后置处理。
 * 
 * 在Spring实例化bean的前后执行一些附加操作。
 * 
 * 有时，你会发现需要立刻在Spring实例化一个bean的前后执行一些附件操作。这些操作可以简单到修改一个bean，也可以复杂到返回一个完全不同的对象。
 * BeanPostProcessor接口包含两个method：
 * postProcessBeforeInitialization方法：在Spring调用任何bean的初始化钩子
 * （例如InitializingBean.afterPropertiesSet或者init方法）之前被调用。
 * postProcessAfterInitialization方法：Spring在成功完成嵌入初始化以后调用他
 * 
 * 不过滤，容器中所有的bean初始化前后都处理．
 * 
 * @author doctor
 *
 * @since 2015年1月1日 上午12:53:17
 */
@Component("instantiationTracingBeanPostProcessor")
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization:" + beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization :" + beanName);
		return bean;
	}

}

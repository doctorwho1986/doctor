package com.doctor.spring.context;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 非spring管理的类实例如何托管给spring容器管理
 * 
 * 下面是摘自spring文档　．
 * pdf版本：spring-framework-reference4.1.3.pdf: http://www.t00y.com/file/81375143
 * 
 * the ApplicationContext implementations also
 * permit the registration of existing objects that are created outside the
 * container, by users. This is done by accessing the ApplicationContext’s
 * BeanFactory via the method getBeanFactory() which returns the BeanFactory
 * implementation DefaultListableBeanFactory. DefaultListableBeanFactory
 * supports this registration through the methods registerSingleton(..) and
 * registerBeanDefinition(..). However, typical applications work solely with
 * beans defined through metadata bean definitions.
 * 
 * @author doctor
 *
 * @since 2014年12月30日 下午10:19:52
 */
public class ApplicationContextBeanRegistrationOfExistingObjects {
	public ExpectedException exception = ExpectedException.none();
	private AnnotationConfigApplicationContext context;
	
	@Autowired
	@Qualifier("testBean")
	private TestBean testBean;
	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(SpringContextConfig.class);
	}

	@Test
	public void test_contextBeanRegistrationOfExistingObjects() {
		ApplicationContextBeanRegistrationOfExistingObjects bean = null;
		try {
			bean = (ApplicationContextBeanRegistrationOfExistingObjects) context.getBean(ApplicationContextBeanRegistrationOfExistingObjects.class);
		} catch (BeansException e) {

		}
		assertNull(bean);

		context.getBeanFactory().registerSingleton("applicationContextBeanRegistrationOfExistingObjects", new ApplicationContextBeanRegistrationOfExistingObjects());
		bean = (ApplicationContextBeanRegistrationOfExistingObjects) context.getBean(ApplicationContextBeanRegistrationOfExistingObjects.class);
		assertNotNull(bean);
		System.out.println(bean);
	}
	
	
	@Test
	public void test_contextBeanRegistrationOfExistingObjects_非托管类属性注入() {
		assertNull(testBean);
		context.getBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
		assertNotNull(testBean);
		System.out.println(testBean);
	}
}

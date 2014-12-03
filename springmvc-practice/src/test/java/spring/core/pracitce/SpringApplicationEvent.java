package spring.core.pracitce;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.core.pracitce.SpringApplicationEventConfigure.Pig;

/**
 * Open Declarationorg.springframework.beans.factory.config.AutowireCapableBeanFactory
 * 
 * Extension of the org.springframework.beans.factory.
 * 
 * BeanFactory interface to be implemented by bean factories that are capable of autowiring,
 * 
 *  provided that they want to expose this functionality for existing bean instances.
 * 
 * This subinterface of BeanFactory is not meant to be used in normal application code: 
 * 
 * stick to org.springframework.beans.factory.BeanFactory or org.springframework.beans.factory.
 * 
 * ListableBeanFactory for typical use cases.
 * 
 * Integration code for other frameworks can leverage this interface to wire 
 * 
 * and populate existing bean instances that Spring does not control the lifecycle of. 
 * 
 * This is particularly useful for WebWork Actions and Tapestry Page objects, for example.
 * 
 * Note that this interface is not implemented by org.springframework.context.ApplicationContext facades, 
 * 
 * as it is hardly ever used by application code. That said, 
 * 
 * it is available from an application context too, accessible through ApplicationContext's
 * 
 * org.springframework.context.ApplicationContext.getAutowireCapableBeanFactory() method.
 * 
 * You may also implement the org.springframework.beans.factory.BeanFactoryAware interface, 
 * 
 * which exposes the internal BeanFactory even when running in an ApplicationContext, 
 * 
 * to get access to an AutowireCapableBeanFactory: simply cast the passed-in BeanFactory to 
 * 
 * AutowireCapableBeanFactory.
 * 
 * 
 * @author
 *
 * @time 2014年12月3日 下午2:29:53
 */
public class SpringApplicationEvent {
	@Autowired
	@Qualifier("pig")
	private Pig Pig;

	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationEventConfigure.class);
		Pig pig = (Pig) context.getBean("pig");
		pig.speak();

	}

	@Test
	public void test_2() {
		//看类注释
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringApplicationEventConfigure.class);
		context.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
		this.Pig.speak();
	}
}

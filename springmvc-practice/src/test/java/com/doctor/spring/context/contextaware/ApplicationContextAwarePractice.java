package com.doctor.spring.context.contextaware;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ApplicationContextAware用处，替代方法，及注意事项 
 * 
 * When an ApplicationContext creates an object
 * instance that implements the
 * org.springframework.context.ApplicationContextAware interface, the instance
 * is provided with a reference to that ApplicationContext.
 * 
 * Thus beans can manipulate programmatically the ApplicationContext that
 * created them, through the ApplicationContext interface, or by casting the
 * reference to a known subclass of this interface, such as
 * ConfigurableApplicationContext, which exposes additional functionality. One
 * use would be the programmatic retrieval of other beans. Sometimes this
 * capability is useful; however, in general you should avoid it, because it
 * couples the code to Spring and does not follow the Inversion of Control
 * style, where collaborators are provided to beans as properties. Other methods
 * of the ApplicationContext provide access to file resources, publishing
 * application events, and accessing a MessageSource. These additional features
 * are described in Section 5.15, “Additional Capabilities of the
 * ApplicationContext” As of Spring 2.5, autowiring is another alternative to
 * obtain reference to the ApplicationContext. The "traditional" constructor and
 * byType autowiring modes (as described in the section called “Autowiring
 * collaborators”) can provide a dependency of type ApplicationContext for a
 * constructor argument or setter method parameter, respectively. For more
 * flexibility, including the ability to autowire fields and multiple parameter
 * methods, use the new annotation-based autowiring features. If you do, the
 * ApplicationContext is autowired into a field, constructor argument, or method
 * parameter that is expecting the ApplicationContext type if the field,
 * constructor, or method in question carries the
 * 
 * @Autowired annotation. For more information, see the section called
 *            “@Autowired”.
 * 
 * @author doctor
 *
 * @since 2014年12月31日 上午1:03:56
 */
public class ApplicationContextAwarePractice {
	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		context = new AnnotationConfigApplicationContext(ApplicationContextAwareConfig.class);
	}

	@Test
	public void test() {
		ContextawareBean contextawareBean = context.getBean(ContextawareBean.class);
		assertNotNull(contextawareBean);
		ApplicationContext applicationContext = contextawareBean.getContext();
		assertNotNull(applicationContext);
	}
}

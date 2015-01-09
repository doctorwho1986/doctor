package com.doctor.spring.context.inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Resource 的作用相当于 @Autowired，只不过 @Autowired 按 byType 自动注入，
 *           面 @Resource 默认按 byName 自动注入罢了。@Resource 有两个属性是比较重要的，
 *           分别是 name 和 type，Spring 将 @Resource 注释的 name 属性解析为 Bean 的名字，
 *           而 type 属性则解析为 Bean 的类型。所以如果使用 name 属性，则使用 byName 的自动注入策略，
 *           而使用 type 属性时则使用 byType 自动注入策略。如果既不指定 name 也不指定 type 属性，
 *           这时将通过反射机制使用 byName 自动注入策略。
 *           一般情况下，我们无需使用类似于 @Resource(type=Car.class) 的注释方式，
 *           因为 Bean 的类型信息可以通过 Java 反射从代码中获取。
 * 
 * @author doctor
 *
 * @time 2015年1月9日 下午4:54:56
 */
public class ResourceAnnotationPractice {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceAnnotationPracticeConfig.class);
		ResourceBean1 bean1 = context.getBean("resourceBean1", ResourceBean1.class);
		System.out.println(bean1.getResourceBean2());
		System.out.println(bean1.getResourceBean3());

		for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}
	}

	@Configuration
	@ComponentScan(basePackages = { "com.doctor.spring.context.inject" })
	public static class ResourceAnnotationPracticeConfig {

	}
}

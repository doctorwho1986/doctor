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
 *           1.注释配置和 XML 配置的适用场合
 * 
 *           是否有了这些 IOC 注释，我们就可以完全摒除原来 XML 配置的方式呢？答案是否定的。有以下几点原因：
 * 
 *           注释配置不一定在先天上优于 XML 配置。如果 Bean 的依赖关系是固定的，（如 Service 使用了哪几个 DAO 类），这种配置信息不会在部署时发生调整，那么注释配置优于 XML 配置；反之如果这种依赖关系会在部署时发生调整，XML 配置显然又优于注释配置，因为注释是对 Java 源代码的调整，您需要重新改写源代码并重新编译才可以实施调整。
 *           如果 Bean 不是自己编写的类（如 JdbcTemplate、SessionFactoryBean 等），注释配置将无法实施，此时 XML 配置是唯一可用的方式。
 *           注释配置往往是类级别的，而 XML 配置则可以表现得更加灵活。比如相比于 @Transaction 事务注释，使用 aop/tx 命名空间的事务配置更加灵活和简单。
 * 
 *           所以在实现应用中，我们往往需要同时使用注释配置和 XML 配置，对于类级别且不会发生变动的配置可以优先考虑注释配置；而对于那些第三方类以及容易发生调整的配置则应优先考虑使用 XML 配置。Spring 会在具体实施 Bean 创建和 Bean 注入之前将这两种配置方式的元信息融合在一起。
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
		System.out.println(bean1.resourceInterfaces);

		for (String beanName : context.getBeanDefinitionNames()) {
			System.out.println(beanName);
		}

		context.close();
	}

	@Configuration
	@ComponentScan(basePackages = { "com.doctor.spring.context.inject" })
	public static class ResourceAnnotationPracticeConfig {

	}
}

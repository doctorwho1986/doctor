package com.github.doctor.dubbo.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.schema.DubboBeanDefinitionParser;

/**
 * 一个进程内（一个jvm）启动多个dubbo应用，注册url让其不重复（用了不同的端口号，改写了dubbo的ServiceConfig 为ServiceConfigEx类，
 * 动手术的地方就是private static Integer getRandomPort(String protocol) 方法。
 * 这个要和扩展的BroadcastResultMergeCluster 一起使用，用于jstorm ，一个jvm进程内启动多个bolt实例，每个bolt
 * 实例是一个dubbo服务。
 * 
 * @author doctor
 *
 * @time 2014年12月26日 下午6:01:56
 */
public class DubboNamespaceHandlerEx extends NamespaceHandlerSupport {

	static {
		Version.checkDuplicate(DubboNamespaceHandlerEx.class);
	}

	public void init() {
		registerBeanDefinitionParser("application", new DubboBeanDefinitionParser(ApplicationConfig.class, true));
		registerBeanDefinitionParser("module", new DubboBeanDefinitionParser(ModuleConfig.class, true));
		registerBeanDefinitionParser("registry", new DubboBeanDefinitionParser(RegistryConfig.class, true));
		registerBeanDefinitionParser("monitor", new DubboBeanDefinitionParser(MonitorConfig.class, true));
		registerBeanDefinitionParser("provider", new DubboBeanDefinitionParser(ProviderConfig.class, true));
		registerBeanDefinitionParser("consumer", new DubboBeanDefinitionParser(ConsumerConfig.class, true));
		registerBeanDefinitionParser("protocol", new DubboBeanDefinitionParser(ProtocolConfig.class, true));
		registerBeanDefinitionParser("reference", new DubboBeanDefinitionParser(ReferenceBean.class, false));
		registerBeanDefinitionParser("annotation", new DubboBeanDefinitionParser(AnnotationBean.class, true));

		registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(ServiceBeanEx.class, true));

	}
}

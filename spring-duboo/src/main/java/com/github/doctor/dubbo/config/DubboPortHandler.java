package com.github.doctor.dubbo.config;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.config.ProtocolConfig;

/**
 * 这是一个进程启动多个dubbo服务的另一种解决方案。以前的方案见DubboNamespaceHandlerEx
 * 利用spring修改受spring管理bean的属性信息
 * 
 * @author doctor
 *
 * @time 2014年12月30日 上午11:34:40
 */
public class DubboPortHandler implements InitializingBean, ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// DubboNamespaceHandler 注册了
		// registerBeanDefinitionParser("protocol", new DubboBeanDefinitionParser(ProtocolConfig.class, true));

		ProtocolConfig protocolConfig = (ProtocolConfig) context.getBean("protocol");
		protocolConfig.setPort(NetUtils.getAvailablePort());

	}

}

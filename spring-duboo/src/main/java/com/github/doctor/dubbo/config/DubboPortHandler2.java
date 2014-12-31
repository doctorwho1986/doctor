package com.github.doctor.dubbo.config;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.config.ProtocolConfig;

/**
 * 这是一个进程启动多个dubbo服务的DubboPortHandler另一种写法。以前的方案见DubboNamespaceHandlerEx
 * 利用spring修改受spring管理bean的属性信息
 * 
 * @author doctor
 *
 * @time 2014年12月31日 上午9:43:52
 */
public class DubboPortHandler2 {

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		Map<String, ProtocolConfig> beansOfType = applicationContext.getBeansOfType(ProtocolConfig.class);
		for (Entry<String, ProtocolConfig> item : beansOfType.entrySet()) {
			item.getValue().setPort(NetUtils.getAvailablePort());
		}
	}
}

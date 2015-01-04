package com.github.dubbo.provider.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.utils.NetUtils;
import com.github.doctor.dubbo.config.DubboPortHandler2;
import com.github.dubbo.common.demo.service.GetIpAndPortService;

@Component("getIpAndPortService")
public class GetIpAndPortServiceImpl implements GetIpAndPortService {

	@Autowired
	private DubboPortHandler2 dubboPortHandler2;

	@Override
	public Map<String, String> getMemoryInfo() {
		Map<String, String> map = new HashMap<>();
		map.put(NetUtils.getLocalAddress().toString()+":" + dubboPortHandler2.getPort(), "GetIpAndPortServiceImpl method");
		return map;
	}

}

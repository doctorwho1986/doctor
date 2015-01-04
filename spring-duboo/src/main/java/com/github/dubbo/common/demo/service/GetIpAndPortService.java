package com.github.dubbo.common.demo.service;

import java.util.Map;

/**
 * 获取dubbo 提供服务的ip + port
 * @author doctor
 *
 * @time   2015年1月4日 上午11:03:41
 */
public interface GetIpAndPortService {
	/**
	 * 
	 * @return map key 为IP+Port, value 根据情况而定
	 */
	Map<String, String> getMemoryInfo();
}

package com.github.dubbo.common.demo.service;

import java.util.List;

/**
 * 测试扩展dubbo集群合并结果，且同一个进程启动多个相同的服务
 * 
 * @author doctor
 *
 * @since 2014年12月28日 上午7:50:48
 */
public interface BroadcastResultMergeService {
	void setClusterName(String clusterName);

	List<String> getClusterName();
}

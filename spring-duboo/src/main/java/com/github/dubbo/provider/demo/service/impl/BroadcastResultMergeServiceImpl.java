package com.github.dubbo.provider.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import com.github.dubbo.common.demo.service.BroadcastResultMergeService;

/**
 * @author doctor
 *
 * @since 2014年12月28日 上午7:51:32
 */
public class BroadcastResultMergeServiceImpl implements BroadcastResultMergeService {

	private String clusterName;

	@Override
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	@Override
	public List<String> getClusterName() {
		return Arrays.asList(clusterName);
	}

}

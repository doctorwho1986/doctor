package com.github.dubbo.provider.demo.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dubbo.common.demo.service.MergeService;

public class MergeServiceImpl implements MergeService {
	private static final Logger log = LoggerFactory.getLogger(MergeServiceImpl.class);
	
	private static final String content = "abcdefghijklmnopqrstuvwxyz";
	@Override
	public List<String> get(int count) {
		String random = RandomStringUtils.random(count, content);
		log.info("{random:'{}'}",random);
		return Arrays.asList(random);
	}

}

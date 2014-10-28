package com.doctor.kafkajstrom.log.manager.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.doctor.kafkajstrom.log.manager.LogManager;

@Component
public class LogManagerImp implements LogManager {
	private static final Logger LOG = LoggerFactory.getLogger(LogManagerImp.class);
	
	@Override
	public void write(String logContent) {
		LOG.info(" LogManagerImp write method :" + logContent);

	}

}

package com.github.netexample.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StaticResourceProcessor {
	protected static final Logger logger = LoggerFactory.getLogger(StaticResourceProcessor.class);
	
	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			logger.info("{sendStaticResource error : {}}",e.getMessage());
		}
	}
}

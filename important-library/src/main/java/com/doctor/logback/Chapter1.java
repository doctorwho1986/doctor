package com.doctor.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * @see http://logback.qos.ch/manual/introduction.html
 * @author doctor
 *
 * @since 2014年10月26日 下午9:34:42
 */
public class Chapter1 {
	private static final Logger LOG = LoggerFactory.getLogger(Chapter1.class);
	
	public static void main(String[] args) {
		LOG.info("hello doctor");
		
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		StatusPrinter.print(loggerContext);

	}

}

package com.gitjub.springmvc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class ServletContextListenerPractice implements ServletContextListener {
	private static final Logger log = LoggerFactory.getLogger(ServletContextListenerPractice.class);
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("{msg:'ServletContextListenerPractice  contextInitialized'}");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("{msg:'ServletContextListenerPractice  contextDestroyed'}");

	}

}

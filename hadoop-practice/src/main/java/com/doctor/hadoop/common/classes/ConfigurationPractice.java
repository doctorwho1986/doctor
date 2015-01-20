package com.doctor.hadoop.common.classes;

import org.apache.hadoop.conf.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author doctor
 *
 * @since 2015年1月21日 上午1:02:31
 */
public class ConfigurationPractice {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationPractice.class);
	
	public static void main(String[] args) {
		LOG.info("---------");
		Configuration configuration = new Configuration(true);
		configuration.setQuietMode(false);
		String name = configuration.get("fs.default.name");
		System.out.println(name);
	}

}

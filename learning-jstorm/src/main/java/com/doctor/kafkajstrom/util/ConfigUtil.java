package com.doctor.kafkajstrom.util;

import java.util.Map.Entry;
import java.util.Properties;

import backtype.storm.Config;

public final class ConfigUtil {
	public static Config of(Properties props){
		Config config = new Config();
		for (Entry<Object, Object> item : props.entrySet()) {
			config.put((String) item.getKey(), item.getValue());
		}
		return config;
	}
	
}

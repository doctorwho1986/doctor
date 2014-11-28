package com.doctor.hadoop.commonpractice;

import org.apache.hadoop.conf.Configuration;

public class ConfigurationPractice {
	
	static{
		System.setProperty("HADOOP_HOME", ConfigurationPractice.class.getResource("").getFile());
		System.setProperty("hadoop.home.dir", ConfigurationPractice.class.getResource("").getFile());
	}

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration();
		configuration.addResource("hodoopPracticeConf/ssl-server.xml");
		configuration.getStrings("ssl.server.truststore.reload.interval");
		System.out.println(configuration.getStrings("ssl.server.truststore.reload.interval")[0]);
		System.out.println(configuration.getStrings("ssl.server.keystore.password")[0]);
		
		configuration.addResource("hodoopPracticeConf/Copysl-server.xml");
		System.out.println(configuration.get("ssl.server.truststore.location"));
		System.out.println(configuration.get("ssl.server.keystore.password"));

	}

}

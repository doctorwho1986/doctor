package com.doctor.jafka;

import java.io.File;
import java.util.Properties;

import com.sohu.jafka.Jafka;
/**
 * 主要是跟踪看一下执行流程
 * 
 * @author doctor
 *
 * @time   2014年10月29日 上午11:34:33
 */


public class MyBroker {

	public static void main(String[] args) {
		
		try (Jafka broker = new Jafka()) {
			Properties mainProperties = new Properties();
			mainProperties.put("brokerid", "88");
			mainProperties.put("port", "9999");
			//本地启动个zookeer集群
			mainProperties.put("enable.zookeeper", "true");
			mainProperties.put("zk.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
			mainProperties.put("log.dir", MyBroker.class.getResource("/").getFile()+ File.separator + "libraryJafkaLog");

			broker.start(mainProperties, null, null);
			broker.awaitShutdown();
		}
	}

}

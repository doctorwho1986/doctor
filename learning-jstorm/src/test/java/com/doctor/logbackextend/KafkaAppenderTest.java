package com.doctor.logbackextend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zookeeper 和kafka环境准备好。本地端口号默认设置
 * 
 * @author doctor
 *
 * @time   2014年10月24日 下午3:14:01
 */
public class KafkaAppenderTest {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaAppenderTest.class);
	

	/** 先启动此测试方法，模拟log日志输出到kafka */
	@Test
	public void test_log_producer() {
		while(true){
			LOG.info("test_log_producer : "  + RandomStringUtils.random(3, "hello doctro,how are you,and you"));
		}
	}
	
	
	/** 再启动此测试方法，模拟消费者获取日志，进而分析，此方法仅仅是打印打控制台，不是log，防止模拟log测试方法数据混淆 */
	@Test
	public void test_comsumer(){
		Properties props = new Properties();
		props.put("zookeeper.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
		props.put("group.id", "kafkatest-group");
//		props.put("zookeeper.session.timeout.ms", "400");
//		props.put("zookeeper.sync.time.ms", "200");
//		props.put("auto.commit.interval.ms", "1000");
		ConsumerConfig paramConsumerConfig = new ConsumerConfig(props );
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(paramConsumerConfig );
		
		Map<String, Integer> topicCountMap = new HashMap<>();
		topicCountMap.put("kafka-test", new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerStream = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerStream.get("kafka-test");
		
		for (KafkaStream<byte[], byte[]> stream : streams) {
			ConsumerIterator<byte[], byte[]> it = stream.iterator();
			while(it.hasNext())
			System.out.println(new String("test_comsumer: " + new String(it.next().message())));
		}
		
		
	}

}

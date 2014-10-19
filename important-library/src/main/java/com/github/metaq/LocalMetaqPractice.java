package com.github.metaq;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;

public class LocalMetaqPractice {
	private static final Logger log = LoggerFactory.getLogger(LocalMetaqPractice.class);
	
	private static final String topic = "test";
	private static final String group = "meta-example";
	
	private static  MetaClientConfig metaClientConfig = new MetaClientConfig();
	private static ZKConfig zkConfig = new ZKConfig();
	private static MetaMessageSessionFactory messageSessionFactory ;
	private static MessageProducer producer;
	private static MessageConsumer consumer;

	private static String zkServers = "192.168.8.8:2181,192.168.8.9:2181,192.168.8.10:2181";
	static{
		zkConfig.setZkConnect(zkServers);
		metaClientConfig.setZkConfig(zkConfig);
		try {
			
			messageSessionFactory = new MetaMessageSessionFactory(metaClientConfig);
			
		} catch (MetaClientException e) {
			
			log.error("{msg:'MetaMessageSessionFactory 创建失败：{}'}",e);
		}
		producer = messageSessionFactory.createProducer();
		consumer = messageSessionFactory.createConsumer(new ConsumerConfig(group));
	}

	@Test
	public void producer(){
		producer.publish(topic);
		String message = "MetaMessageSessionFactory";
		String temp ="";
		while(true){
			try {
				temp = RandomStringUtils.random(6, message);
				SendResult sendResult = producer.sendMessage(new Message(topic, temp.getBytes(StandardCharsets.UTF_8)));
				if (sendResult.isSuccess()) {
					log.info("{msg:'send message successfully -- {}'}",temp);
				}else {
					log.error("{msg:'send message fail -- {}'}",temp);
				}
			} catch (MetaClientException | InterruptedException e) {
				
				log.error("{msg:'sendMessage error: {}'}",e);
			}
			
			
		}
	}
	
	@Test
	public void consumer() throws MetaClientException{
		consumer.subscribe(topic, 1024*1024, new MessageListener() {
			
			@Override
			public void recieveMessages(Message message) throws InterruptedException {
				log.info("{msg:'receive message: {}'}",new String(message.getData(),StandardCharsets.UTF_8));
			}
			
			@Override
			public Executor getExecutor() {
				return null;
			}
		});
		
		consumer.completeSubscribe();
		
		while(true){
			
		}
	}
}

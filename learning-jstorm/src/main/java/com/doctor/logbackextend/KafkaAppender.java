package com.doctor.logbackextend;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

public class KafkaAppender extends AppenderBase<ILoggingEvent> {

	private String topic;
	private String zookeeperHost;
	

	private String broker;
	private Producer<String, String> producer;
	private Formatter formatter;
	
	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}
	@Override
	protected void append(ILoggingEvent eventObject) {
		String message = this.formatter.formate(eventObject);
		this.producer.send(new KeyedMessage<String, String>(this.topic, message));

	}

	@Override
	public void start() {
		if (this.formatter == null) {
			this.formatter = new MessageFormatter();
		}
		
		super.start();
		Properties props = new Properties();
		props.put("zk.connect", this.zookeeperHost);
		props.put("metadata.broker.list", this.broker);
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		
		ProducerConfig config = new ProducerConfig(props);
		this.producer = new Producer<String, String>(config);
	}

	@Override
	public void stop() {
		super.stop();
		this.producer.close();
	}

	
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getZookeeperHost() {
		return zookeeperHost;
	}

	public void setZookeeperHost(String zookeeperHost) {
		this.zookeeperHost = zookeeperHost;
	}

	public Producer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(Producer<String, String> producer) {
		this.producer = producer;
	}


	public Formatter getFormatter() {
		return formatter;
	}

	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}
	
	
	
	/**
	 * 格式化日志格式
	 * @author doctor
	 *
	 * @time   2014年10月24日 上午10:37:17
	 */
	interface Formatter{
		String formate(ILoggingEvent event);
	}
	
	public static class MessageFormatter implements Formatter{

		@Override
		public String formate(ILoggingEvent event) {
			
			return event.getFormattedMessage();
		}
		
	}
}

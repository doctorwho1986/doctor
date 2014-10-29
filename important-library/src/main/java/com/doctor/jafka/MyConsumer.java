package com.doctor.jafka;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sohu.jafka.consumer.Consumer;
import com.sohu.jafka.consumer.ConsumerConfig;
import com.sohu.jafka.consumer.ConsumerConnector;
import com.sohu.jafka.consumer.MessageStream;
import com.sohu.jafka.producer.serializer.StringDecoder;
import com.sohu.jafka.utils.ImmutableMap;

public class MyConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zk.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
		props.put("groupid", "test-jafka");
		//
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector connector = Consumer.create(consumerConfig);
		//
		Map<String, List<MessageStream<String>>> topicMessageStreams = connector.createMessageStreams(ImmutableMap.of("myBroker", 2), new StringDecoder());
		List<MessageStream<String>> streams = topicMessageStreams.get("myBroker");
		//
		for (MessageStream<String> stream : streams) {
			for (String message : stream) {
				System.out.println(message);
			}
		}
		
	}

}

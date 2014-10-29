package com.doctor.jafka;

import java.util.Properties;

import com.sohu.jafka.producer.Producer;
import com.sohu.jafka.producer.ProducerConfig;
import com.sohu.jafka.producer.StringProducerData;
import com.sohu.jafka.producer.serializer.StringEncoder;

public class MyProducer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zk.connect", "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183");
		props.put("serializer.class", StringEncoder.class.getName());
		
		StringProducerData producerData = new StringProducerData("myBroker");
		for(int i = 0; i < 100; i++){
			producerData.add("hello doctor, I am the #" + i); 
		}
		
		try(Producer<String, String> producer = new Producer<>(new ProducerConfig(props))) {
			
			while(true){
				producer.send(producerData);
			}
		}
		
	}

}

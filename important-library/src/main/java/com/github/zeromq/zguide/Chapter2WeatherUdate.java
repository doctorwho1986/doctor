package com.github.zeromq.zguide;



import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

/**
 * 
 * @author see http://zguide.zeromq.org/page:all
 *
 */
public class Chapter2WeatherUdate {
	private Logger log = LoggerFactory.getLogger(Chapter2WeatherUdate.class);
	
	@Test
	public void wuserver() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket publisher = context.socket(ZMQ.PUB);
		publisher.bind("tcp://*:5566");
		publisher.bind("ipc://weather");
		
		log.info("{msg:'publisher starting.....'}");
		
		String random = "abcdef";
		
		while (!Thread.currentThread().isInterrupted()) {
			String send = RandomStringUtils.random(5, random);
			publisher.send(send, 0);
//			log.info("{msg:'publish {}'}",send);
		}
		
		publisher.close();
		context.term();
	}
	
	@Test
	public void wcclient(){
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket subscriber = context.socket(ZMQ.SUB);
		subscriber.connect("tcp://localhost:5566");
		subscriber.subscribe("abcde".getBytes());
		log.info("{msg:'subscriber starting...'}");
		
		for(int i = 0; i < Integer.MAX_VALUE; i++){
			String recvStr = subscriber.recvStr(0);
			log.info("{msg:'subsriber {}'}",recvStr);
		}
		
		subscriber.close();
		context.term();
	}
}

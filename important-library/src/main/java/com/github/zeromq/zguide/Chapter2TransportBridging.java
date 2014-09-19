package com.github.zeromq.zguide;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Chapter2TransportBridging {

	/**
	 * 
	 * @param see http://zguide.zeromq.org/page:all#Transport-Bridging
	 */
	public static void main(String[] args) {
		
		//pub sub 启动顺序注意
	
		new Thread(() -> wuClient()).start();
		new Thread(() -> wuClient()).start();
		new Thread(() -> wuClient()).start();
		
		new Thread(() -> wuServer()).start();
		
		wuproxy();
		

	}

	
	public static void wuproxy() {
		Context context = ZMQ.context(1);
		Socket server = context.socket(ZMQ.PUB);
		server.bind("tcp://*:9999");
		
		Socket client = context.socket(ZMQ.SUB);
		client.connect("tcp://localhost:8888");
		client.subscribe("".getBytes());
		ZMQ.proxy(client, server, null);
		
		client.close();
		server.close();
		context.term();
	}
	
	public static void wuServer() {
		Context context = ZMQ.context(1);
		Socket server = context.socket(ZMQ.PUB);
		server.bind("tcp://*:8888");
		
		try {
			while (!Thread.currentThread().isInterrupted()) {
				String threadName = Thread.currentThread().getName();
				String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(6);
				server.send("server: " + threadName + " " + randomAlphanumeric, ZMQ.PAIR);
				System.out.println("server: " + threadName + " " + randomAlphanumeric);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			server.close();
			context.term();
		}
	}
	
	public static void wuClient() {
		Context context = ZMQ.context(1);
		Socket client = context.socket(ZMQ.SUB);
		client.connect("tcp://localhost:9999");
		client.subscribe("".getBytes());
		
		while (!Thread.currentThread().isInterrupted()) {
			String recvStr = client.recvStr(ZMQ.PAIR);
			System.out.println("client " + Thread.currentThread().getName() + "  "  + recvStr);
		}
		
		client.close();
		context.term();
	}
}

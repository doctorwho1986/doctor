package com.github.zeromq.zguide;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.zeromq.ZMQ;

public class Chapter2HandlingMultipleSockets {

	public static void main(String[] args) {
		
		new Thread(()-> server1()).start();
		new Thread(()-> server2()).start();
		new Thread(()-> client()).start();

	}
	
	
	private static void client() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket sub = context.socket(ZMQ.SUB);
		sub.connect("tcp://localhost:6666");
		sub.subscribe("abcd".getBytes());
		
		ZMQ.Socket pull = context.socket(ZMQ.PULL);
		pull.connect("tcp://localhost:8888");
		
		ZMQ.Poller poller = new ZMQ.Poller(2);
		poller.register(sub, ZMQ.Poller.POLLIN);
		poller.register(pull, ZMQ.Poller.POLLIN);
		
		while (!Thread.currentThread().isInterrupted()) {
			poller.poll();
			if (poller.pollin(0)) {
				String recvStr = sub.recvStr(ZMQ.PAIR);
				System.out.println("sub: " + recvStr);
			}
			
			if (poller.pollin(1)) {
				String recvStr = pull.recvStr(ZMQ.PAIR);
				System.out.println("pull : " + recvStr);
			}
		}
		
		sub.close();
		pull.close();
		context.term();
		
	}
	public static void server1() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket pub = context.socket(ZMQ.PUB);
		pub.bind("tcp://*:6666");
		System.out.println("server1 starting....");
		try {
			while (!Thread.currentThread().isInterrupted()) {
				pub.send("abcd", ZMQ.PAIR);
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			pub.close();
			context.term();
		}
	}

	
	public static void server2() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket push = context.socket(ZMQ.PUSH);
		push.bind("tcp://*:8888");
		
		System.out.println("server1 starting........");
		try {
			while (!Thread.currentThread().isInterrupted()) {
				push.send(RandomStringUtils.randomAlphanumeric(2), ZMQ.PAIR);
				
				TimeUnit.SECONDS.sleep(2);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			push.close();
			context.term();
		}
	}
}

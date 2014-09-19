package com.github.zeromq.zguide;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;

/**
 * 
 * @author see http://zguide.zeromq.org/page:all#Divide-and-Conquer
 *
 */
public class Chapter2DivideAdConquer {
	private static final Logger log = LoggerFactory.getLogger(Chapter2DivideAdConquer.class);

	public static void main(String[] args) {
		new Thread(() -> tasksink()).start();
		new Thread(() -> taskwork()).start();
		new Thread(() -> taskwork()).start();
		new Thread(() -> taskwork()).start();
		new Thread(() -> taskwork()).start();
		new Thread(() -> ventilator()).start();

	}

	/*
	 * 
	 * Task ventilator in Java
	 * Binds PUSH socket to tcp://localhost:5557
	 * Sends batch of tasks to workers via that socket
	 * 
	 * Here is the ventilator. It generates 100 tasks, each a message telling the worker to
	 * sleep for some number of milliseconds:
	 * 消息产生源头 推送消息服务器
	 */
	public static void ventilator() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket push = context.socket(ZMQ.PUSH);
		push.bind("tcp://*:5557"); //消息推送服务器

		ZMQ.Socket sink = context.socket(ZMQ.PUSH);
		sink.connect("tcp://localhost:5558");
		sink.send("start", 0); //为了使得中间多级处理器pull消息不能丢失，必须先启动sink服务器
		log.info("{msg:'消息推送服务器 start'}");

		try {
			for (int i = 0; i < 30; i++) {
				String randomAlphanumeric = RandomStringUtils.randomAlphanumeric(8);
				push.send(randomAlphanumeric, 0);
				TimeUnit.SECONDS.sleep(1);
				log.info("{消息推送服务器 push: {}}", randomAlphanumeric);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		log.info("{msg:'消息推送服务器 closed'}");
		push.close();
		sink.close();
		context.term();
	}

	/**
	 * ask worker in Java
	 * Connects PULL socket to tcp://localhost:5557
	 * Collects workloads from ventilator via that socket
	 * Connects PUSH socket to tcp://localhost:5558
	 * Sends results to sink via that socket
	 * 
	 * 中间并行处理流节点，并行
	 */
	public static void taskwork() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket pull = context.socket(ZMQ.PULL);
		pull.connect("tcp://localhost:5557"); //pull 消息服务器的消息

		ZMQ.Socket push = context.socket(ZMQ.PUSH);
		push.connect("tcp://localhost:5558"); //push给sink服务器 消息

		log.info("{msg:'taskwork {} start'}",Thread.currentThread().getName());
		
		try {
			while (!Thread.currentThread().isInterrupted()) {
				String recvStr = pull.recvStr(0);
				recvStr = recvStr + "---" + Thread.currentThread().getName();
				log.info("{taskwork {} pull : {}}", Thread.currentThread().getName(), recvStr);
				TimeUnit.SECONDS.sleep(1);
				push.send(recvStr, 0);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		pull.close();
		push.close();
		context.term();
	}

	/**
	 *
	 * Task sink in Java
	 * Binds PULL socket to tcp://localhost:5558
	 * Collects results from workers via that socket
	 * 
	 * sink 消息服务器
	 */
	public static void tasksink() {
		ZMQ.Context context = ZMQ.context(1);
		ZMQ.Socket pull = context.socket(ZMQ.PULL);
		pull.bind("tcp://*:5558");

		String start = pull.recvStr(0); // 先得到消息推送服务器启动消息，然后自身启动开始pull消息，阻塞，如果无消息
		log.info("{msg:'tasksink start'}");
		while (!Thread.currentThread().interrupted()) {
			String recvStr = pull.recvStr(0);
			log.info("{tasksink {} pull: {}}", Thread.currentThread().getName(), recvStr);
		}

		pull.close();
		context.term();
	}
}

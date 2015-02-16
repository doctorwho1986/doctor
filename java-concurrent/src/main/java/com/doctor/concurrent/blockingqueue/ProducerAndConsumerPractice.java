package com.doctor.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see {@code BlockingQueue} 文档
 * @author doctor
 *
 * @time 2015年2月16日 下午2:53:42
 */
public class ProducerAndConsumerPractice {

	public static void main(String[] args) {
		ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(20);

		new Thread(new Producer(arrayBlockingQueue)).start();
		new Thread(new Consumer(arrayBlockingQueue)).start();

	}

	private static class Producer implements Runnable {
		private static final Logger log = LoggerFactory.getLogger(Producer.class);
		private final BlockingQueue<String> blockingQueue;

		public Producer(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String value = String.valueOf(Math.random());
					blockingQueue.put(value);
					log.info("生产者:" + Thread.currentThread().getName() + " put " + value);
					TimeUnit.SECONDS.sleep(3);

				}
			} catch (InterruptedException e) {
				log.error("生产者:" + Thread.currentThread().getName(), e);
			}

		}

	}

	private static class Consumer implements Runnable {
		private static final Logger log = LoggerFactory.getLogger(Consumer.class);
		private final BlockingQueue<String> blockingQueue;

		public Consumer(BlockingQueue<String> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String take = blockingQueue.take();
					log.info("消费者:" + Thread.currentThread().getName() + "take:" + take);

					TimeUnit.SECONDS.sleep(10);
				}
			} catch (InterruptedException e) {
				log.error("消费者:" + Thread.currentThread().getName(), e);
			}

		}

	}

}

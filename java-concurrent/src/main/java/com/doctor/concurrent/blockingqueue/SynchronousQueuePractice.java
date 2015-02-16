package com.doctor.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @see http://javapapers.com/java/java-synchronousqueue/
 * 
 *      SynchronousQueue. It is an implementation of BlockingQueue. Among all Java concurrent
 *      collections, SynchronousQueue is different. Capacity of a synchrounous queue is always zero.
 *      It is because in SynchronousQueue an insert will wait for a remove operation by another
 *      thread and vice versa.
 * @author doctor
 *
 * @time 2015年2月16日 下午3:14:24
 */
public class SynchronousQueuePractice {

	/**
	 * 
	 * 1.put() call to a SynchronousQueue will not return until there is a corresponding take()
	 * call.
	 * 2.peek is not possible with a SynchronousQueue
	 * As there is no element iteration is also not possible.
	 * 3.Insert is not possible if there is a thread trying to remove it.
	 * 
	 * SynchronousQueue should be imagined like a baton in a relay race.
	 * If there are more than one thread waiting for a removal so that they can do insert then with
	 * fairness set to true, threads are granted access in FIFO order.
	 * SynchronousQueue is the default BlockingQueue used for the Executors.newCachedThreadPool()
	 * methods.
	 */
	public static void main(String[] args) {
		SynchronousQueue<String> synchronousQueue = new SynchronousQueue<String>();
		new Thread(new Producer(synchronousQueue)).start();
		new Thread(new Producer(synchronousQueue)).start();
		new Thread(new Producer(synchronousQueue)).start();
		new Thread(new Consumer(synchronousQueue)).start();

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
					TimeUnit.SECONDS.sleep(1);

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

					TimeUnit.SECONDS.sleep(2);
				}
			} catch (InterruptedException e) {
				log.error("消费者:" + Thread.currentThread().getName(), e);
			}

		}

	}

}

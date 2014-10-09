package com.github.jdk.java_util_concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author @link http://tutorials.jenkov.com/java-util-concurrent/blockingqueue.html
 * 
 *         A BlockingQueue with one thread putting into it, and another thread taking from it.
 * 
 *         A BlockingQueue has 4 different sets of methods for inserting, removing and examining the elements in the queue. Each set of methods behaves differently in case the requested operation cannot be carried out immediately. Here is a table of the methods:
 *         -------Throws Exception ----Special Value ----Blocks --------Times Out
 *         Insert --add(o) -------------offer(o) --------put(o) --------offer(o, timeout, timeunit)
 *         Remove --remove(o) ----------poll(o) ---------take(o) --------poll(timeout, timeunit)
 *         Examine-- element(o)--------- peek(o)
 * 
 *         The 4 different sets of behaviour means this:
 * 
 *         Throws Exception:
 *         If the attempted operation is not possible immediately, an exception is thrown.
 *         Special Value:
 *         If the attempted operation is not possible immediately, a special value is returned
 *         (often true / false).
 *         Blocks:
 *         If the attempted operation is not possible immedidately, the method call blocks until it is.
 *         Times Out:
 *         If the attempted operation is not possible immedidately, the method call blocks until it is,
 *         but waits no longer than the given timeout. Returns a special value telling whether
 *         the operation succeeded or not (typically true / false).
 * 
 *         It is not possible to insert null into a BlockingQueue. If you try to insert null,
 *         the BlockingQueue will throw a NullPointerException.
 * 
 *         It is also possible to access all the elements inside a BlockingQueue,
 *         and not just the elements at the start and end. For instance, say you have queued
 *         an object for processing, but your application decides to cancel it.
 *         You can then call e.g. remove(o) to remove a specific object in the
 *         queue. However, this is not done very efficiently,
 *         so you should not use these Collection methods unless you really have to.
 */

public class BlockingQueuePractice {
	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(100);
		Producer producer = new Producer(blockingQueue);
		Consumer consumer = new Consumer(blockingQueue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		
	}

	public static class Producer implements Runnable {
		private BlockingQueue<Integer> blockingQueue;

		public Producer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					System.out.println("producer:" + i);
					blockingQueue.put(i);
					TimeUnit.SECONDS.sleep(2);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static class Consumer implements Runnable {
		private BlockingQueue<Integer> blockingQueue;

		public Consumer(BlockingQueue<Integer> blockingQueue) {
			this.blockingQueue = blockingQueue;
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 1000; i++) {
					TimeUnit.SECONDS.sleep(3);
					Integer take = blockingQueue.take();
					System.out.println("consumer:" + take);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}

package com.github.jdk.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueuePractice {

	public static void main(String[] args) {
		BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(19);
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Producer(blockingQueue)).start();
		new Thread(new Consumer(blockingQueue)).start();
		

	}

}

class Producer implements Runnable {
	private BlockingQueue<Integer> blockingQueue;
	private boolean isProducing = true;

	public Producer(BlockingQueue<Integer> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			
			while (isProducing) {
				TimeUnit.SECONDS.sleep(3);
				if (blockingQueue.remainingCapacity() == 0) {
					System.err.println("full");
				} else {
					int nextInt = new Random().nextInt(100);
					System.out.println("produce :" + nextInt);
					blockingQueue.put(nextInt);
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public boolean isProducing() {
		return isProducing;
	}

	public void setProducing(boolean isProducing) {
		this.isProducing = isProducing;
	}

}

class Consumer implements Runnable{
	private BlockingQueue<?> blockingQueue;
	private boolean isConsuming = true;
	
	public Consumer(BlockingQueue<?> blockingQueue){
		this.blockingQueue = blockingQueue;
	}
	public boolean isConsuming() {
		return isConsuming;
	}
	public void setConsuming(boolean isConsuming) {
		this.isConsuming = isConsuming;
	}
	
	@Override
	public void run() {
		try {
			
			while (isConsuming) {
				TimeUnit.SECONDS.sleep(3);
				System.out.println("consume :" + blockingQueue.take());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}

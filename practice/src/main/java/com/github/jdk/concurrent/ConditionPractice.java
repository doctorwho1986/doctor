/*
 * Copyright (C) 2009 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jdk.concurrent;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author doctor
 *
 * @date 2014年8月23日 下午12:30:16
 */
public class ConditionPractice {

	public static void main(String[] args) {
		Store2 store2 = new Store2(10);
		
		new Thread(new Producer31(store2)).start();
		new Thread(new Producer31(store2)).start();
		new Thread(new Producer31(store2)).start();
		new Thread(new Consumer31(store2)).start();
		new Thread(new Consumer31(store2)).start();

	}

}

class Store2 {
	private int capacity;
	private Queue<Instant> queue;
	private Lock lock = new ReentrantLock();
	private Condition readCondition = lock.newCondition();
	private Condition writeCondition = lock.newCondition();

	public Store2(int capactiy) {
		this.capacity = capactiy;
		queue = new ArrayDeque<Instant>(capactiy);
		queue.clear();
	}

	public void put(Instant instant) {
		lock.lock();
		try {
			while (capacity == queue.size()) {
				writeCondition.await();
			}
			queue.add(instant);
			readCondition.signalAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public Instant getInstant() {
		lock.lock();
		Instant instant = null;
		try {

			while (0 == queue.size()) {
				readCondition.await();
			}

			instant = queue.poll();
			writeCondition.signalAll();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return instant;
	}
}

class Producer31 implements Runnable {

	private Store2 store;

	public Producer31(Store2 store) {
		this.store = store;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Instant instant = Instant.now();
				store.put(instant);
				System.out.println("put instant: " + instant);
				TimeUnit.SECONDS.sleep(3);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Consumer31 implements Runnable{

	private Store2 store;
	public Consumer31(Store2 store){
		this.store = store;
	}
	@Override
	public void run() {
		try {
			while(true){
				Instant instant = store.getInstant();
				System.out.println("get instant: " + instant);
				TimeUnit.SECONDS.sleep(1);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

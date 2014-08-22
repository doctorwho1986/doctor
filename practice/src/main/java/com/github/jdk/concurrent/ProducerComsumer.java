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

/**
 * @author doctor
 *
 * @date 2014年8月22日 下午10:03:18
 */
public class ProducerComsumer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Store<Instant> store = new Store(20);
		new Thread(new Producer_1(store)).start();
		new Thread(new Consumer_1(store)).start();
		new Thread(new Consumer_1(store)).start();
	}

}

class Store<T> {
	private int capacity;
	private Queue<T> queue;

	public <T> Store(int capactiy) {
		this.capacity = capactiy;
		queue = new ArrayDeque<>(capactiy);
	}

	public synchronized void put(T value) {
		while (queue.size() == capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		queue.add(value);
		System.out.println("put value :" + value + " size: " + queue.size());
		notifyAll();
	}

	public synchronized void getValue() {
		while (queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("get value: " + queue.poll());
	}
}

class Producer_1 implements Runnable {
	private Store<Instant> store;

	public Producer_1(Store<Instant> store) {
		this.store = store;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < 50; i++) {
				store.put(Instant.now());
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Consumer_1 implements Runnable {
	private Store<Instant> store;

	public Consumer_1(Store<Instant> store) {
		this.store = store;
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i < 55; i++) {
				store.getValue();
				TimeUnit.SECONDS.sleep(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

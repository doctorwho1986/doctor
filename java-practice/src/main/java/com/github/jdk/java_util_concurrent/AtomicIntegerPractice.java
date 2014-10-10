package com.github.jdk.java_util_concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

public class AtomicIntegerPractice {
	
	@Test
	public void test_1(){
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Assert.assertEquals(1, atomicInteger.incrementAndGet());
		Assert.assertEquals(1, atomicInteger.getAndIncrement());
		Assert.assertEquals(2, atomicInteger.get());
		Assert.assertEquals(1, atomicInteger.decrementAndGet());
		Assert.assertEquals(1, atomicInteger.getAndDecrement());
		Assert.assertEquals(0, atomicInteger.get());
	}
	
	@Test
	public void test_2() throws InterruptedException{
		int n = 100;
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Thread[] threads = new Thread[n];
		for (int i = 0; i < n; i++) {
			threads[i] = new Thread(() -> System.out.println(atomicInteger.incrementAndGet()));
		}
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			thread.join();
		}
		
		Assert.assertEquals(n, atomicInteger.get());
	}

}

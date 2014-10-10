package com.github.jdk.java_util_concurrent;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author @link http://www.blogjava.net/xylz/archive/2010/07/03/325168.html
 * 
 *         在Java Concurrency in Practice中是这样定义线程安全的：
 * 
 *         当多个线程访问一个类时，如果不用考虑这些线程在运行时环境下的调度和交替运行，
 *         
 *         并且不需要额外的同步及在调用方代码不必做其他的协调，这个类的行为仍然是正确的，那么这个类就是线程安全的。
 * 
 *         显然只有资源竞争时才会导致线程不安全，因此无状态对象永远是线程安全的。
 * 
 *         原子操作的描述是： 多个线程执行一个操作时，其中任何一个线程要么完全执行完此操作，要么没有执行此操作的任何步骤，
 *         那么这个操作就是原子的。
 */
public class AtomicIntegerPractice {

	@Test
	public void test_1() {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Assert.assertEquals(1, atomicInteger.incrementAndGet());
		Assert.assertEquals(1, atomicInteger.getAndIncrement());
		Assert.assertEquals(2, atomicInteger.get());
		Assert.assertEquals(1, atomicInteger.decrementAndGet());
		Assert.assertEquals(1, atomicInteger.getAndDecrement());
		Assert.assertEquals(0, atomicInteger.get());
	}

	@Test
	public void test_2() throws InterruptedException {
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

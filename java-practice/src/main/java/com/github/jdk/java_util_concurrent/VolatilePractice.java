package com.github.jdk.java_util_concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 
 * @author @link http://www.blogjava.net/xylz/archive/2010/07/03/325168.html
 * 
 *         volatile语义         
 * 
 *         volatile相当于synchronized的弱实现，也就是说volatile实现了类似synchronized的语义，却又没有锁机制。
 *         
 *         它确保对volatile字段的更新以可预见的方式告知其他的线程。
 * 
 *         volatile包含以下语义：
 * 
 *         （1）Java 存储模型不会对valatile指令的操作进行重排序：这个保证对volatile变量的操作时按照指令的出现顺序执行的。
 * 
 *         （2）volatile变量不会被缓存在寄存器中（只有拥有线程可见）或者其他对CPU不可见的地方，
 *         
 *         每次总是从主存中读取volatile变量的结果。
 *         
 *         也就是说对于volatile变量的修改，其它线程总是可见的，并且不是使用自己线程栈内部的变量。
 *         
 *         也就是在happens-before法则中，对一个valatile变量的写操作后，其后的任何读操作理解可见此写操作的结果。
 * 
 *         尽管volatile变量的特性不错，但是volatile并不能保证线程安全的，也就是说volatile字段的操作不是原子性的，
 *         
 *         volatile变量只能保证可见性（一个线程修改后其它线程能够理解看到此变化后的结果），要想保证原子性，目前为止只能加锁！
 * 
 *         
 * 
 *         应用volatile变量的三个原则：
 * 
 *         （1）写入变量不依赖此变量的值，或者只有一个线程修改此变量
 * 
 *         （2）变量的状态不需要与其它变量共同参与不变约束
 * 
 *         （3）访问变量不需要加锁
 * 
 *
 */
public class VolatilePractice {
	private volatile int v = 0;
	private int mum = Integer.MAX_VALUE;

	@Test
	public void test_1() throws InterruptedException {
		Random random = new Random();

		Thread update = new Thread(() -> {

			while (mum > 0) {
				v = random.nextInt();
				System.out.println("set :" + v);
				mum--;
				try {
					TimeUnit.MICROSECONDS.sleep(1);

				} catch (Exception e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	);

		update.start();
		int n = 20;
		Thread[] read = new Thread[n];
		for (int i = 0; i < n; i++) {
			read[i] = new Thread(() -> System.out.println(v));
		}
		for (Thread thread : read) {
			thread.start();
		}
	}

	@Test
	public void test_wrong() throws InterruptedException {
		Random random = new Random();
		int updateS = 10;
		Thread[] update = new Thread[updateS];
		for (int i = 0; i < updateS; i++) {
			update[i] = new Thread(() -> {

				while (mum > 0) {
					v = random.nextInt();
					System.out.println("set :" + v);
					mum--;
					try {
						TimeUnit.MICROSECONDS.sleep(1);

					} catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	);
		}

		for (Thread thread : update) {
			thread.start();
		}

		int n = 20;
		Thread[] read = new Thread[n];
		for (int i = 0; i < n; i++) {
			read[i] = new Thread(() -> System.out.println(v));
		}
		for (Thread thread : read) {
			thread.start();
		}
	}
}

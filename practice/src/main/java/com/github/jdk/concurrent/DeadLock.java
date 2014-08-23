package com.github.jdk.concurrent;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author doctor
 *
 * @date 2014年8月23日 下午8:46:37
 */
public class DeadLock {
	/**
	 * 1、linux 命令 kill -3 pid 对于线程状态观察,eclipse console 会输出
	 * 2、java 命令 jstack pid 观察线程状态。
	 * @param args
	 */
	public static void main(String[] args) {
		
		//得到jvm线程 pid ，用命令观察线程死锁信息 pid@域名
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		DeadLockAB deadLockAB = new DeadLockAB();
		new Thread(new Dead1(deadLockAB)).start();
		new Thread(new Dead2(deadLockAB)).start();

	}

}

class DeadLockAB{
	private Object a = new Object();
	private Object b = new Object();
	
	public void callAB() {
		synchronized (a) {
			System.out.println(Thread.currentThread().getName() + " get a");
			synchronized (b) {
				System.out.println(Thread.currentThread().getName() + " get b");
			}
			System.out.println(Thread.currentThread().getName() + " release b");
		}
		System.out.println(Thread.currentThread().getName() + " release a");
	}
	
	public void callBA() {
		synchronized (b) {
			System.out.println(Thread.currentThread().getName() + "get b");
			synchronized (a) {
				System.out.println(Thread.currentThread().getName() + " get a");
			}
			System.out.println(Thread.currentThread().getName() + " release a");
		}
		System.out.println(Thread.currentThread().getName() + " release b");
	}
}

class Dead1 implements Runnable{

	private DeadLockAB deadLockAB;
	
	public Dead1(DeadLockAB deadLockAB) {
		this.deadLockAB = deadLockAB;
	}
	@Override
	public void run() {
		try {
			while(true){
				deadLockAB.callAB();
				TimeUnit.SECONDS.sleep((int)Math.random()*10L);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


class Dead2 implements Runnable{

	private DeadLockAB deadLockAB;
	
	public Dead2(DeadLockAB deadLockAB) {
		this.deadLockAB = deadLockAB;
	}
	@Override
	public void run() {
		try {
			while(true){
				deadLockAB.callBA();
				TimeUnit.SECONDS.sleep((int)Math.random()*10L);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
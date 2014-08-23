package com.github.jdk.concurrent;

import java.util.concurrent.TimeUnit;


/**
 * 
 * @author doctor
 *
 * @date 2014年8月23日 下午8:46:37
 */
public class DeadLock {

	public static void main(String[] args) {
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
package com.github.jdk.concurrent;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ThreadLocalPractice {

	public static void main(String[] args) {
		ThreadLocalVar var = new ThreadLocalVar();
		for (int i = 0; i < 10; i++) {
			
			new Thread(var).start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ThreadSharVar sharVar = new ThreadSharVar();
		for (int i = 0; i < 10; i++) {
			
			new Thread(sharVar).start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

class ThreadLocalVar implements Runnable{

	private ThreadLocal<Instant> date = new ThreadLocal<Instant>(){
		@Override
		protected Instant initialValue(){
			return Instant.now();
		}
	};
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "   start time : " + date.get());
		try {
			TimeUnit.SECONDS.sleep((int)Math.random()*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "   end time : " + date.get());	}
}

class ThreadSharVar implements Runnable{
	private AtomicReference<Instant> instant = new AtomicReference<Instant>();

	@Override
	public synchronized void run() {
		instant.set(Instant.now());
		System.out.println(Thread.currentThread().getName() + " share  start time : " + instant.get());
		try {
			TimeUnit.SECONDS.sleep((int)Math.random()*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instant.set(Instant.now());
		
		System.out.println(Thread.currentThread().getName() + "share  end time : " + instant.get());	
		
	}
}
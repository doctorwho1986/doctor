package com.github.jdk.java_util_concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapPractice {

	public static void main(String[] args) {
		ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<>();
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.submit(()->{
			try {
				for (int i = 0; i < 10; i++) {
					concurrentHashMap.put(i, i);
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		executorService.submit(()->{
			try {
				for (int i = 0; i < 10; i++) {
					TimeUnit.SECONDS.sleep(2);
					System.out.println(concurrentHashMap.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		executorService.shutdown();
	}

}

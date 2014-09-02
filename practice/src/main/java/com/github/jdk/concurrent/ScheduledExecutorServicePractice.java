package com.github.jdk.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServicePractice {

	public static void main(String[] args) {
		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
		 scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				
				System.out.println(LocalDateTime.now());
				
			}
		}, 4, 10, TimeUnit.SECONDS);

	}

}

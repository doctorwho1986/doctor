package com.github.quartz;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JdkQuartz {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(()->{
			System.out.println(JdkQuartz.class.getName() + "scheduleAtFixedRate");
		}, 1, 2, TimeUnit.SECONDS);
	}

}

package com.github.jdk.java_util_concurrent;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author
 * @link http://markusjais.com/understanding-java-util-concurrent-completionservice/
 * 
 *       The java.util.concurrent.CompletionService is a useful interface in the JDK standard libraries
 * 
 *       but few developers know it.
 * 
 *       One could live without it as you can of course program this functionality with the other interfaces
 * 
 *       and classes within java.util.concurrent but it is convenient to have a solution that is already
 * 
 *       available and less error prone then doing it yourself. I always prefer stuff that is already
 * 
 *       available within the JDK over implementing my own solution with the same features –
 * 
 *       unless as an exercise at home!
 * 
 *       Image you have a list of separate tasks that take a while, e.g. 10 tasks that each download
 * 
 *       an URL and return the content as a String.
 * 
 *       Depending on the network, the size of the downloaded content and other factors,
 * 
 *       the time to download each URL will take various amounts of time.
 * 
 *       When you execute them in parallel you may want to start doing something with the
 * 
 *       downloaded content as soon as the first task is done. No need to wait for the other
 * 
 *       9 tasks to complete because that would mean you would always have to wait until all URLs
 * 
 *       are downloaded before doing something useful with each individual result.
 * 
 *       You can of course execute all of the and get a List of Future objects
 * 
 *       and then poll on each one but it is easier to just use a CompletionService.
 * 
 * 
 *       As soon as a task is completed, it is put in an internal java.util.concurrent.BlockingQueue
 * 
 *       (a highly efficient queue for Producer/Consumer problems and communication between threads).
 * 
 *       From that queue, you can get the results of the finished tasks with take. If no task is yet available,
 * 
 *       take will wait until something is available.
 * 
 *       In this case we just print the result (the name of the current threat executing the Callable).
 */

public class CompletionServicePractice {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final int threadNum = 10;
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);
		ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(fixedThreadPool);

		for (int i = 0; i < threadNum; i++) {
			completionService.submit(() -> {
				int sleep = 0;
				try {
					sleep = Math.abs(new Random().nextInt(30));
					TimeUnit.SECONDS.sleep(Math.abs(new Random().nextInt(10)));
				} catch (Exception e) {
					e.printStackTrace();
				}

				return Thread.currentThread().getName() + " : " + sleep;
			});
		}

		for (int i = 0; i < threadNum; i++) {
			Future<String> future = completionService.take();
			System.out.println(future.get());
		}

		fixedThreadPool.shutdown();
	}

}

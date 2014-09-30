package com.github.netty5.nettyinaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




import java.util.concurrent.Future;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class Chapter1 {
	@Test
	public void test_future_example_via_ExecutorService(){
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		executorService.submit(() ->{
			for (int i = 0; i < Long.MAX_VALUE; i++) {
				System.out.println("1");
			}
		});
		
		Future<String> future2 = executorService.submit(() -> {
			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				
			}
			return RandomStringUtils.random(2,"abcdefg");
		});
		
		executorService.shutdown();
		
		while (!future2.isDone()) {
			System.out.println("future2 is not done");
		}
		
		try {
			System.out.println(future2.get().toString());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

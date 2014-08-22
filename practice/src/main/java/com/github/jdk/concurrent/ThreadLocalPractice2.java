package com.github.jdk.concurrent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.tuple.Pair;
/**
 * 
 * @author doctor
 *
 * @date 2014年8月22日 下午8:58:11
 */

public class ThreadLocalPractice2 {

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		ThreadLocalVar2 localVar2 = new ThreadLocalVar2();
		java.util.List<Future<Pair<Instant, Instant>>> list = 
				  new ArrayList<Future<Pair<Instant, Instant>>>();
		
		for (int i = 0; i < 10; i++) {
			Future<Pair<Instant, Instant>> future = threadPool.submit(localVar2);
			list.add(future);
		}
		threadPool.shutdown();
		int size = list.size();
		Pair<Instant, Instant> future = null;
		try {
			for (int i = 0; i < size; i++) {
				future = list.get(i).get();
				
				System.out.println(future.getLeft() + "  :  " + future.getRight());
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}

}

class ThreadLocalVar2 implements Callable<Pair<Instant, Instant>>{
	private ThreadLocal<Pair<Instant, Instant>> instants = new ThreadLocal<Pair<Instant,Instant>>();
	@Override
	public Pair<Instant, Instant> call() throws Exception {
		Instant startInstant = Instant.now();
		TimeUnit.SECONDS.sleep((int)(Math.random()*10 + 0.5D));
		Instant endInstant = Instant.now();
		instants.set(Pair.of(startInstant,endInstant));
		
		return instants.get();
	}
}


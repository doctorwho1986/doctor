package com.github.doctor.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceArray;

import org.junit.Before;
import org.junit.Test;

/**
 * @author doctor
 *
 * @since 2014年12月28日 下午8:39:12
 */
public class AtomicReferenceArrayTest {
	private AtomicReferenceArray<Integer> atomicReferenceArray;
	
	@Before
	public void init(){
		atomicReferenceArray = new AtomicReferenceArray<>(6);
	}
	
	@Test
	public void test(){
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0 ;i < 1000 ; i++){
			final int index = i%6;
			
			service.submit(()->{
				
				atomicReferenceArray.set(index, index);
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		for(int i = 0 ;i < 1000 ; i++){
			final int index = i%6;
			
			service.submit(()->{
				
				atomicReferenceArray.set(index, index);
				System.out.println(Thread.currentThread().getName() + ": " + index);
			});
		}
		for(int i = 0 ;i < 1000 ; i++){
			final int index = i%6;
			
			service.submit(()->{
				
				atomicReferenceArray.set(index, index);
				System.out.println(Thread.currentThread().getName() + ": " + index);
			});
		}
		service.shutdown();
		for(int i = 0; i < 6; i++){
			System.out.println(atomicReferenceArray.get(i));
		}
	}
}

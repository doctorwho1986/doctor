package com.github.jdk.java_util_concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * 
 * @author
 * @link http://www.ibm.com/developerworks/cn/java/j-concurrent/
 *
 */
public class JavaConcurrencyInPracticeBook {

	/**
	 * 调试大量的线程

有时一个程序因为有大量的线程在运行而极难调试。在这种情况下，下面的这个类可能会派上用场：

public class Probe extends Thread {
    public Probe() {}
    public void run() {
        while(true) {
            Thread[] x = new Thread[100];
            Thread.enumerate(x);
            for(int i=0; i<100; i++) {
            Thread t = x[i];
            if(t == null)
                break;
            else
                System.out.println(t.getName() + "\t" + t.getPriority()
                + "\t" + t.isAlive() + "\t" + t.isDaemon());
            }
        }
    }
}
	 */
	
	@Test
	public void test_调试大量的线程(){
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		threadPool.submit(()->{
			while(true){
				
			}
		});
		
		threadPool.submit(()->{
			while(true){
				
			}
		});
		threadPool.shutdown();
		
		Thread[] tarray = new Thread[1024];
		int length = Thread.enumerate(tarray );
		for (int i = 0; i < length; i++) {
			Thread t = tarray[i];
			if (null!= t) {
				System.out.println(t.getName() + "\t" + t.getPriority() + "\t" + t.isAlive() + "\t" + t.isDaemon());
			}
		}
	}
}

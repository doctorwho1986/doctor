
package com.github.jdk.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author doctor
 *
 * @date 2014年8月23日 下午1:41:38
 */
public class SemaphorePractice {

	public static void main(String[] args) {
		PrintJob printJob = new PrintJob();
		new Thread(new PrintThread(printJob),"thread" + 1).start();
		new Thread(new PrintThread(printJob),"thread" + 2).start();
		new Thread(new PrintThread(printJob),"thread" + 3).start();
		new Thread(new PrintThread(printJob),"thread" + 4).start();
		new Thread(new PrintThread(printJob),"thread" + 5).start();

	}

}

class PrintJob{
	private Semaphore semaphore = new Semaphore(1);
	public void printJobs(Object object) {
		try {
			semaphore.acquire();
			System.out.println("print job :" + Thread.currentThread().getName() + " :: " + object );
			TimeUnit.SECONDS.sleep((int)(Math.random() *10L ));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
	}
}

class PrintThread implements Runnable{
	private PrintJob printJob;
	public PrintThread(PrintJob printJob) {
		this.printJob = printJob;
	}
	@Override
	public void run() {
		printJob.printJobs(this.getClass());
	}
}

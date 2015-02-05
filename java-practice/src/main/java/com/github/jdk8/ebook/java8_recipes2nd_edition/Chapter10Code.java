package com.github.jdk8.ebook.java8_recipes2nd_edition;

/**
 * @author doctor
 *
 * @since 2015年2月5日 下午8:24:29
 */
public class Chapter10Code {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"is running in the background");
			}, "background thread");
		thread.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() +" is counting :" +i);
		}

	}

}

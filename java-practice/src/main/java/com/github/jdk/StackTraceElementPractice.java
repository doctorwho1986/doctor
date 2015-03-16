package com.github.jdk;

/**
 * Java如何取源文件中文件名和行号
 * 
 * {@code StackTraceElement}的定义详见文档
 * 
 * @author doctor
 *
 * @time 2015年3月13日 上午9:16:16
 */
public class StackTraceElementPractice {

	public static void main(String[] args) {

		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		System.out.println(stackTrace.length);
		StackTraceElement stackTraceElement = stackTrace[stackTrace.length - 1];
		System.out.println(stackTraceElement.getFileName());
		System.out.println(stackTraceElement.getLineNumber());
		//输出：
		//2
		//StackTraceElementPractice.java
		//16
	}

}

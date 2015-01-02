package com.github.java.runtime;

/**
 * 获得处理器核心数，为了更好的确定线程数
 * Returns the number of processors available to the Java virtual machine. This
 * value may change during a particular invocation of the virtual machine.
 * Applications that are sensitive to the number of available processors should
 * therefore occasionally poll this property and adjust their resource usage
 * appropriately.
 * 
 * @author doctor
 *
 * @since 2015年1月2日 上午11:40:19
 */
public class AvaliableProcessors {
	public static void main(String[] args) {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(processors);//我的电脑是４核，确实输出的是４
	}
}

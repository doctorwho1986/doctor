package com.github.jdk7;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * try ，finally都抛出异常如何处理．如果try中抛出了异常，在控制权转移到调用栈上一层代码之前，
 * finally　语句块也会执行，如果finally抛出异常，try语句快抛出的那个异常就丢失了．
 * 
 * @author doctor
 *
 * @since 2014年12月14日 下午10:54:58
 */
public class ExceptionalHandling {
	private static final Logger log = LoggerFactory.getLogger(ExceptionalHandling.class);

	@Test(expected = ArithmeticException.class)
	public void test_wrong_way() {
		try {
			Integer.parseInt("hello");
		} catch (NumberFormatException e) {
			throw e;
		} finally {
			try {
				int a = 10 / 0;
			} catch (ArithmeticException e) {
				throw e;
			}
		}
	}

	/**
	 * 会得到的异常堆栈信息： 
	 * [main] ERROR com.github.jdk7.ExceptionalHandling -
	 * java.lang.NumberFormatException: For input string: "hello"
	 *         at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65) ~[na:1.8.0_25]
	 *         .............
	 *         Suppressed: java.lang.ArithmeticException: / by zero
			   at com.github.jdk7.ExceptionalHandling.test_right_way(ExceptionalHandling.java:43) ~[classes/:na]
	 */
	@Test(expected = NumberFormatException.class)
	public void test_right_way() {
		RuntimeException throwable = null;
		try {
			Integer.parseInt("hello");
		} catch (NumberFormatException e) {
			throwable = e;
		} finally {
			try {
				int a = 10 / 0;
			} catch (ArithmeticException e) {
				if (throwable == null) {
					throwable = e;
				} else {
					throwable.addSuppressed(e);//异常保留
				}
			}
		}

		if (throwable != null) {
			log.error("", throwable);
			throw throwable;
		}
	}
}

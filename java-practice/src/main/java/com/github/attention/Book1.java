package com.github.attention;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book1 {
	private static final Logger log = LoggerFactory.getLogger(Book1.class);
	
	@Test
	public void test_1(){
		int i = 90;
		String number1 = String.valueOf(i < 100 ? 90 :100);
		String number2 = String.valueOf(i < 100 ? 90 :100.0);
		log.info("{number1 is :{}, number2 is : {}}",number1,number2);
		Assert.assertFalse(number1.equals(number2));
		
		int num1 = i < 100 ?90 :100;
		//num2 如果是int，编译器会提示
		double num2 = i < 100? 90 :100.0;
		
		System.out.println(num1 + ":" + num2);
	}
	
	
	@Test
	public void test_2(){
		int count = 0;
		for (int i = 0; i < 10; i++) {
			count = count++;
			log.info("{count:'{}'}",count);
			Assert.assertEquals(0, count);
		}
		
		count = 0;
		for (int i = 0; i < 10; i++) {
			count++;
			log.info("{count:'{}'}",count);
			Assert.assertEquals(i+1, count);
		}
	}
	
	@Test
	public void test_goto(){
		test_2:test_2();
	}
	
	
	

}


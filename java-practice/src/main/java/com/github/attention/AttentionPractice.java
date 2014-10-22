package com.github.attention;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AttentionPractice {

	@Rule
	public final ExpectedException excetion = ExpectedException.none();
	
	@Test
	public void test_不要让四舍五入亏了一方(){
		assertThat(Math.round(10.5), is(11L));
		assertThat(Math.round(-10.5), is(-10L));
		
		assertThat(Math.round(10.5551), is(11L));
		
		BigDecimal bigDecimal = BigDecimal.valueOf(11.354);
		BigDecimal bigDecimal2 = bigDecimal.valueOf(2.0);
		BigDecimal bigDecimal3 = bigDecimal.multiply(bigDecimal2).setScale(2, RoundingMode.HALF_EVEN);
		
		assertThat(bigDecimal3.toString(), is("22.71"));
		
	}
	
	@Test
	public void test_包装类型Null值(){
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(null);
		
		assertTrue(list.stream().count()==2);
		
		int sum = 0;
		//还是用java自带算法吧。自己遍历还得考虑null值
		excetion.expect(NullPointerException.class);
		for (Integer integer : list) {
			sum += integer;
		}
	}
	
	@Test
	public void test_包装类型比较_优先使用对象池(){
		Integer a = Integer.valueOf(12);
		Integer b = Integer.valueOf(12);
		
		//缓存
		assertTrue(a == b);
		
		a = Integer.valueOf(128);
		b = Integer.valueOf(128);
		//无缓存
		assertTrue(a != b);
		assertTrue(0 == a.compareTo(b));
		
		//无缓存对象
		a = new Integer(12);
		b = new Integer(12);
		assertTrue(a != b);
		assertTrue(0 == a.compareTo(b));
	}
	
	@Test
	public void test_工具类不可实例化() throws ReflectiveOperationException{
		Constructor<TestS> constructor = TestS.class.getDeclaredConstructor(new Class[]{});
		constructor.setAccessible(true);
		TestS instance = constructor.newInstance();
		instance.print();
		
	}
	
	@Test
	public void test_工具类不可实例化2() throws ReflectiveOperationException{
		Constructor<TestS2> constructor = TestS2.class.getDeclaredConstructor(new Class[]{});
		constructor.setAccessible(true);
		
		excetion.expect(InvocationTargetException.class);
		TestS2 instance = constructor.newInstance();
		instance.print();
		
	}
}

 final class TestS{
	private TestS(){
		
	}
	
	public  void print() {
		System.out.println("hello doctor");
	}
}
 
 final class TestS2{
	 private TestS2(){
			throw new Error("don't new an instance " + TestS2.class.getName());
		}
		
		public  void print() {
			System.out.println("hello doctor");
		}
 }
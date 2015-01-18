package com.github.jdk8;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * @see http://www.tutorialspoint.com/java8/java8_quick_guide.htm
 * 
 *      1. lambda expressions are used primarily to define inline implementation
 *      of a functional interface i.e. an interface with a single method only.
 *      
 *      2.Lambda expression elliminates the need of anonumous class and gives a
 *      very simple yet powerful functional programming capability to JAVA.
 * @author doctor
 *
 * @since 2015年1月18日 下午3:41:42
 */
public class Java8Overview {

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("name", "doctor", "sex", "address");
		List<String> arrayList = new ArrayList<>(list);

		// java8以前的排序
		Collections.sort(arrayList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return o1.compareTo(o2);
			}
		});
		System.out.println(list);
		System.out.println(arrayList);

		List<String> arrayList2 = new ArrayList<>(list);
		// java8　函数式编程
		Collections.sort(arrayList2, (a, b) -> a.compareTo(b));
		System.out.println(arrayList2);

		//
		MathOperation add = (int a, int b) -> a + b;
		System.out.println(add.operation(5, 5));
		System.out.println(operate(5, 5, add));
		System.out.println(operate(5, 5, (a, b) -> a + b));
		
		//
		Car car = new Car();
		car.print();

		//
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);
		LocalDateTime localDateTime2 = localDateTime.plus(3,ChronoUnit.DAYS );
		System.out.println(localDateTime2);
		System.out.println(Period.between(localDateTime.toLocalDate(), localDateTime2.toLocalDate()));
		System.out.println(Duration.between(localDateTime, localDateTime2));
	}

	private static interface MathOperation {
		int operation(int a, int b);
	}

	private static int operate(int a, int b, MathOperation operation) {
		return operation.operation(a, b);
	}
	
	private interface Vehicle{
		default void print(){
			System.out.println(Vehicle.class.getSimpleName());
		}
		
		public static void blowHorn(){
			System.out.println(Vehicle.class.getSimpleName()+" : blowHorn ");
		}
	}
	
	private interface FourWheeler{
		default void print(){
			System.out.println(FourWheeler.class.getSimpleName());
		}
	}
	
	private static class Car implements Vehicle,FourWheeler{

		@Override
		public void print() {
			System.out.println(Car.class.getSimpleName());
			Vehicle.super.print();
			FourWheeler.super.print();
			Vehicle.blowHorn();
		}
		
	}

}

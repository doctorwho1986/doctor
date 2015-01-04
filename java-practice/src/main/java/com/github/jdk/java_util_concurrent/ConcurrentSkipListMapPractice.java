package com.github.jdk.java_util_concurrent;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * ConcurrentSkipListMap is an implementation of ConcurrentNavigableMap provided in the JDK since 1.6.
 * 
 * The elements are sorted based on their natural sorting order of keys.
 * 
 * The order can be customized using a Comparator provided during the time of initialization.
 * 
 * @author doctor
 *
 * @time 2015年1月4日 下午2:02:35
 */
public class ConcurrentSkipListMapPractice {

	public static void main(String[] args) {
		ConcurrentSkipListMap<String, String> skipListMap = new ConcurrentSkipListMap<>();
		skipListMap.put("3", "name");
		skipListMap.put("1", "sex");
		skipListMap.put("6", "address");
		skipListMap.put("5", "age");

		assertThat(skipListMap.ceilingKey("3"), equalTo("3"));
		assertThat(skipListMap.ceilingKey("2"), equalTo("3"));
		assertThat(skipListMap.ceilingKey("4"), equalTo("5"));

		assertThat(skipListMap.floorKey("4"), equalTo("3"));
		assertThat(skipListMap.floorKey("2"), equalTo("1"));

		assertThat(skipListMap.firstKey(), equalTo("1"));
		assertThat(skipListMap.lastKey(), equalTo("6"));

		// 升序
		for (Entry<String, String> item : skipListMap.entrySet()) {
			System.out.println(item.getKey() + ":" + item.getValue());
		}

		System.out.println("=====================");
		// 反序
		for (Entry<String, String> item : skipListMap.descendingMap().entrySet()) {
			System.out.println(item.getKey() + ":" + item.getValue());
		}

	}

}

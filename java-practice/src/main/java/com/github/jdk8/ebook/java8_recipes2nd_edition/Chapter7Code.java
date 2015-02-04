package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * p185
 * @author doctor
 *
 * @time   2015年2月4日 下午5:32:33
 */
public class Chapter7Code {

	public static void main(String[] args) {
		//map stream
		Map<String, String> map = new HashMap<>();
		map.put("name", "doctor");
		map.put("sex", "man");
		map.put("age", "20000");
		System.out.println("map stream------------");
		map.forEach((k,v)-> System.out.println(k+":"+v));
		
		//list stream
		List<String> list = Arrays.asList("name","sex","age","address");
		System.out.println("list stream-----------");
		list.parallelStream().forEach(System.out::println);
		
		//array stream
		int[] array = {1,33,55,66,77,88,99,100};
		System.out.println("array stream-----------------");
		Arrays.stream(array).forEach(System.out::println);

	}

}

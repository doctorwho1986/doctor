package com.github.apache.collections4;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.commons.collections4.map.MultiValueMap;


public class MutliValueMapPractice {
	public static void main(String[] args) {
		MultiValueMap<String,String> map = new MultiValueMap<>();
		map.put("1", "2");
		map.put("1", "33");
		for (Entry<String, Object> item : map.entrySet()) {
			System.out.println(item.getKey() + ":" );
			ArrayList<String> value =  (ArrayList<String>) item.getValue();
			value.forEach(System.out::println);
		}
	}
}

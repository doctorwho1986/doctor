package com.doctor.guava18;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.collect.Maps;



public class JoinerPractice {

	public static void main(String[] args) {
		List<String> list = new  ArrayList<>();
		list.add("name");
		list.add("who");
		list.add(null);
		list.add("class");
		
		String join = Joiner.on('|').skipNulls().join(list);
		System.out.println(join);
		
		
		System.out.println(Joiner.on('|').useForNull("null").join(list));
		
		
		StringBuilder stringBuilder = new StringBuilder(256);
		Joiner.on('|').useForNull("null").appendTo(stringBuilder, list);
		System.out.println(stringBuilder);
		
		Map<String,String> hashMap = Maps.newHashMap();
		hashMap.put("name", "doctor");
		hashMap.put("key", "null");
		System.out.println(Joiner.on('&').useForNull("null").withKeyValueSeparator("=").join(hashMap));

		Map<String, Object> map = Maps.newHashMap();
		map.put("name", "doctorwho");
		map.put("sex", null);
		
		try {
			MapJoiner mapJoiner = Joiner.on('&').withKeyValueSeparator("=");
			
			System.out.println(mapJoiner.join(map));
		} catch (Throwable e) {
			
			System.out.println("不能有ｎｕｌｌ　ｖａｌｕｅ");
		}
		
		System.out.println(Joiner.on('&').withKeyValueSeparator("=").useForNull("null").join(map));
	}

}

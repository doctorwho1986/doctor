package com.doctor.dubbo.common.utils;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.dubbo.common.utils.LRUCache;

public class LRUCachePractice {
	private LRUCache<String, Integer> lruCache ;
	
	@Before
	public void init(){
		lruCache = new LRUCache<>(6);
	}
	
	@Test
	public void test_lru(){
		lruCache.put("1", 1);
		lruCache.put("2", 2);
		lruCache.put("3", 3);
		lruCache.put("4", 4);
		lruCache.put("5", 5);
		lruCache.put("6", 6);
		lruCache.put("7", 7);
		lruCache.forEach((k,v)->{
			System.out.println(k+" : " + v);
		});
		assertThat(lruCache.size(), equalTo(6));
		
		System.out.println("---------");
		lruCache.put("5", 55);
		assertThat(lruCache.size(), equalTo(6));
		lruCache.forEach((k,v)->{
			System.out.println(k+" : " + v);
		});
		
	}
}

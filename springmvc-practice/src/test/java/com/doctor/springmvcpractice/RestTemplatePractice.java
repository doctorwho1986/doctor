package com.doctor.springmvcpractice;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplatePractice {
	/**
	 * 根据参数Class<T> responseType responseType 
	 * 去找对应 @link{HttpMessageConverter }转换响应内容
	 */
	@Test
	public void test_getForObject1(){
		
		RestTemplate restTemplate = new RestTemplate();
		String forObject = restTemplate.getForObject("http://www.baidu.com/baidu?wd=csdn&tn=monline_dg", String.class);
		System.out.println(forObject);
		
	}
	@Test
	public void test_getForEntity(){
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://www.baidu.com/baidu?wd=csdn&tn=monline_dg", String.class);
		
		System.out.println("-------------------");
		System.out.println("getStatusCode:" + responseEntity.getStatusCode());
		System.out.println("getBody:" + responseEntity.getBody());
		Set<Entry<String,List<String>>> entrySet = responseEntity.getHeaders().entrySet();
		
		System.out.println("--------headers-----------");
		for (Entry<String, List<String>> entry : entrySet) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		
	}
}

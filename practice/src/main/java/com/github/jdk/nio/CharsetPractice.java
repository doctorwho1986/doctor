package com.github.jdk.nio;

import java.nio.charset.Charset;
import java.util.SortedMap;

import org.junit.Assert;

/**
 * 
 * @author doctor
 * @date:2014年 08月 20日 星期三 23:03:18 CST
 */
public class CharsetPractice {

	public static void main(String[] args) {

		SortedMap<String, Charset> availableCharsets = Charset.availableCharsets();
		for (String key : availableCharsets.keySet()) {
			System.out.println(availableCharsets.get(key));
		}
		
		Charset charset = Charset.forName("utf-8");
		System.out.println(charset.displayName());
		Assert.assertTrue(Charset.isSupported("utf-8"));
		Assert.assertTrue(Charset.isSupported("utf-9"));
	}

}

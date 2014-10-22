package com.github.springcorepractice;

import java.nio.charset.StandardCharsets;

import org.springframework.util.Base64Utils;

public class Base64UtilsPractice {

	public static void main(String[] args) {
		String code = "name:doctor who";
		String base64Code = Base64Utils.encodeToString(code.getBytes(StandardCharsets.UTF_8));
		System.out.println(base64Code);
		byte[] base64Decode = Base64Utils.decodeFromString(base64Code);
		System.out.println(new String(base64Decode,StandardCharsets.UTF_8));

	}

}

package com.github.jdk8;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @see http://examples.javacodegeeks.com/core-java/util/base64/java-8-base64-encoding-example/
 * @author doctor
 *
 */
public class Java8Base64EncodingExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// basic
		
		String hi = "hello doctor who!";
		String encodeToString = Base64.getEncoder().encodeToString(hi.getBytes(StandardCharsets.UTF_8));
		System.out.println("encodeToString:" + encodeToString);

		byte[] decode = Base64.getDecoder().decode(encodeToString);
		System.out.println("decode:" + new String(decode, StandardCharsets.UTF_8));

		// url
		
		// This one is very similar to the basic encoder.
		// It uses the URL and Filename safe base64 alphabet
		// and does not add any line separation.
		// This alphabet does not use special characters used in URLs like ‘/’
		String url = "http://music.baidu.com/search?key=refrain";
		String ecodeUrl = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
		System.out.println("ecodeUrl:" + ecodeUrl);
		System.out.println("basic ecode:" + Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8)));

		byte[] decodeUrl = Base64.getUrlDecoder().decode(ecodeUrl);
		System.out.println("url decode:" + new String(decodeUrl, StandardCharsets.UTF_8));

		// mime
		
		// The MIME encoding uses the base64 alphabet as well for encoding and inserts line separators using a ‘\r’ followed by a ‘\n’ (return
		// + end of line). It does not insert a line separator at the end of the output if not needed.
		// Lines have 76 characters.
		//We can see in the provided output how lines have a length of 76 chars but the last one.
		StringBuilder stringBuilder = new StringBuilder();
		String random = "dkjfdfksjfkjsdkfj  sdjf+-2345dkfjkd";
		for (int i = 0; i < 30; i++) {
			stringBuilder.append(RandomStringUtils.random(6, random));
		}

		System.out.println("mime:" + stringBuilder);
		String mimeEncode = Base64.getMimeEncoder().encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
		System.out.println("mimeEncode:\n" + mimeEncode);
	}
}

package com.github.ebook.theartofencryptionandeecryptionaboutjava;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.junit.Test;

public class Chapter5Test {
	private static Encoder urlEncoder = Base64.getUrlEncoder();
	private static Decoder urlDecoder = Base64.getUrlDecoder();

	@Test
	public void test_urlBase64() {
		String message = "hello=doctor";
		String encodeToString = urlEncoder.encodeToString(message.getBytes(StandardCharsets.UTF_8));
		System.out.println(encodeToString);
		
		byte[] decode = urlDecoder.decode(encodeToString);
		String mesageDecode = new String(decode,StandardCharsets.UTF_8);
		System.out.println(mesageDecode);
		assertTrue(message.equals(mesageDecode));
	}

}

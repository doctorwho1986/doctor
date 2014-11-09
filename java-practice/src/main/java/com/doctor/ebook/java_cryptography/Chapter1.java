package com.doctor.ebook.java_cryptography;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.xml.bind.DatatypeConverter;

public class Chapter1 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		System.out.println(digest.getAlgorithm());
		System.out.println(digest.getDigestLength());
		System.out.println(digest.getProvider());
		byte[] bs = digest.digest("hello doctor".getBytes(StandardCharsets.UTF_8));
		Encoder encoder = Base64.getEncoder();
		String encodeToString = encoder.encodeToString(bs);
		System.out.println(encodeToString);
		System.out.println(DatatypeConverter.printBase64Binary(bs));
	}
	
	
	
	

}

package com.doctor.ebook.java_cryptography;

import static org.junit.Assert.*;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.Test;

public class Chapter1Test {

	@Test
	public void test_1_5_2_secretWriting() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(new SecureRandom());
		Key secretKey = keyGenerator.generateKey();
		
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] doFinal = cipher.doFinal("hello doctor".getBytes(StandardCharsets.UTF_8));
		System.out.println(DatatypeConverter.printBase64Binary(doFinal));
		
		
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decrypt = cipher.doFinal(doFinal);
		System.out.println(new String(decrypt,StandardCharsets.UTF_8));
		assertTrue("hello doctor".equals(new String(decrypt,StandardCharsets.UTF_8)));
	}

}

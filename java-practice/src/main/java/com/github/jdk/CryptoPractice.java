package com.github.jdk;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Assert;

public class CryptoPractice {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey_t = keyGenerator.generateKey();
		byte[] secretKey = secretKey_t.getEncoded();

		Key key = new SecretKeySpec(secretKey, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		String hello = "hello word";
		byte[] doFinal = cipher.doFinal(hello.getBytes(StandardCharsets.UTF_8));
		System.out.println(Base64.getEncoder().encodeToString(doFinal));

		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] doFinal2 = cipher.doFinal(doFinal);
		String hello2 = new String(doFinal2,StandardCharsets.UTF_8);
		System.out.println(hello2);
		
		Assert.assertEquals(hello, hello2);
	}

}

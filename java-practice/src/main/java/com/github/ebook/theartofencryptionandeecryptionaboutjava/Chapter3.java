package com.github.ebook.theartofencryptionandeecryptionaboutjava;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;
import java.util.Map.Entry;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;

public class Chapter3 {
	@Test
	public void test_get_Provider(){
		//系统配置的全部安全提供者
		Provider[] providers = Security.getProviders();
		for (Provider provider : providers) {
			System.out.println(provider);
			for (Entry<Object, Object> entry : provider.entrySet()) {
				System.out.println("------" + entry.getKey() + ": " + entry.getValue());
			}
		}
	}
	
	@Test
	public void test_MessageDigest() throws NoSuchAlgorithmException{
		String hello = "Hello word";
		MessageDigest messageDigest = MessageDigest.getInstance("SHA");
		messageDigest.update(hello.getBytes(StandardCharsets.UTF_8));
		byte[] digest = messageDigest.digest();
		System.out.println(DatatypeConverter.printBase64Binary(digest));
	}
	
	@Test
	public void test_des() throws NoSuchAlgorithmException, IOException{
		BigInteger bigInteger = new BigInteger("19050619766489163472469");
		AlgorithmParameters des = AlgorithmParameters.getInstance("DES");
		des.init(bigInteger.toByteArray());
		byte[] encoded = des.getEncoded();
		System.out.println(new BigInteger(encoded).toString());
		Assert.assertEquals(bigInteger, new BigInteger(encoded));
		System.out.println(des.getAlgorithm());
		System.out.println(des.getProvider());
		System.out.println(des.toString());
	}
	
	@Test
	public void test_HmacMD5() throws NoSuchAlgorithmException, InvalidKeyException{
		//Page 72
		byte[] input = "MAC".getBytes(StandardCharsets.UTF_8);
		KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
		SecretKey secretKey = keyGenerator.generateKey();
		
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		byte[] doFinal = mac.doFinal(input);
		System.out.println(DatatypeConverter.printBase64Binary(doFinal));
		System.out.println(Base64.getEncoder().encodeToString(doFinal));
		
	}
}

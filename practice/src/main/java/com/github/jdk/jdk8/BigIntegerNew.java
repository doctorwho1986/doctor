package com.github.jdk.jdk8;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;

/**
 * 
 * @author 
 *  BigInteger 新增的方法 会检查溢出情况
 *  public byte byteValueExact()
 *	public int intValueExact()
 *	public long longValueExact() 
 */
public class BigIntegerNew {
	
	public static void main(String[] args) {
		BigInteger bigInteger = BigInteger.valueOf(Long.MAX_VALUE);
		
		//溢出
		Assert.assertEquals(-1,bigInteger.shortValue());
		try {
			bigInteger.shortValueExact();
		} catch (ArithmeticException e) {
			System.err.println(e.getMessage());
		}
		
	}

}

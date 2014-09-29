package com.github.jdk;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ByteBufferPractice {
	private static final Logger log = LoggerFactory.getLogger(ByteBufferPractice.class);
	
	@Test
	public void test1(){
		int[] numbers = {11,33,66,88};
		ByteBuffer byteBuffer = serializeIntArray(numbers);
		
		Assert.assertEquals((1 +numbers.length) * Integer.BYTES, byteBuffer.limit());
		
		int[] deserializeIntArray = deserializeIntArray(byteBuffer);
		Assert.assertTrue(Arrays.equals(numbers,deserializeIntArray));
	}
	
	private static ByteBuffer serializeIntArray(int[] numbers){
		int size = Integer.BYTES + numbers.length * Integer.BYTES ;
		ByteBuffer byteBuffer = ByteBuffer.allocate(size);
		byteBuffer.putInt(numbers.length);
		for (int number : numbers) {
			byteBuffer.putInt(number);
		}
		byteBuffer.rewind();
		return byteBuffer;
		
	}
	
	private static int[] deserializeIntArray(ByteBuffer byteBuffer){
		int size = byteBuffer.getInt();
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = byteBuffer.getInt();
		}
		return array;
	}
	
	
}

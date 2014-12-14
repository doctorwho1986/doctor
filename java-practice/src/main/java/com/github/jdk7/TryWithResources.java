package com.github.jdk7;

import org.junit.Test;

/**
 * @author doctor
 *
 * @since 2014年12月14日 下午11:42:59
 */
public class TryWithResources {
	
	@Test
	public void test(){
		try (CustomResource customResource = new CustomResource()){
			throw new RuntimeException("test");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private class CustomResource implements AutoCloseable{

		@Override
		public void close() throws Exception {
			System.out.println(getClass() + "< close >");
		}
		
		
	}
}

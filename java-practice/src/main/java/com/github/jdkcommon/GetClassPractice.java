package com.github.jdkcommon;
import static org.junit.Assert.*;

public class GetClassPractice {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		assertTrue(b instanceof A);
		assertTrue(b instanceof B);
		assertTrue(a instanceof A);
		assertFalse(a instanceof B);
		
		
		assertTrue(a.getClass() == A.class);
		assertFalse(a.getClass() == B.class);
		assertTrue(b.getClass() == B.class);
		
		
		assertTrue(test(a,A.class));
		assertFalse(test(a,B.class));
		assertFalse(test(b,A.class));
		assertTrue(test(b,B.class));
		
	}
	
	private static boolean test(Object object,Class<?> type){
		return (object.getClass() == type);
	}
	
	private static class A{}
	private static class B extends A{}

}

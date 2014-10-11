package com.github.jdk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Assert;



public class AnnotationPresentPractice {
	public static void main(String[] args) {
		
		Assert.assertTrue(AnnotationPressent.class.isAnnotationPresent(MyAnnotation.class));
		Assert.assertTrue(MyAnnotation.class.isAnnotation());
		
	}
}

@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{
	
}

@MyAnnotation 
class AnnotationPressent{
	
}
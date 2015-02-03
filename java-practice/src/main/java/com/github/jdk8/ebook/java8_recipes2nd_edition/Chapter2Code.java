package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;


/**
 * java8 新增的@Repeatable注解，其实只是语法糖而已．
 * java8 注解的 {@link RepeatAnn} 类与 {@link Annotations}是等价的．
 * 新注解讲语法糖转化为注解值为数组形式．
 * @author doctor
 *
 * @since 2015年2月3日 下午8:33:43
 */
public class Chapter2Code {

	public static void main(String[] args) {
		Annotation[] annotations = RepeatAnn.class.getAnnotations();
		System.out.println(annotations.length); //1
		Arrays.stream(annotations).forEach(System.out::println);//@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Roles(value=[@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=doctor), @com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=who)])

		Annotation[] annotations2 = Annotations.class.getAnnotations();
		System.out.println(annotations2.length);//1
		Arrays.stream(annotations2).forEach(System.out::println);//@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Roles(value=[@com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=doctor), @com.github.jdk8.ebook.java8_recipes2nd_edition.Chapter2Code$Role(name=who)])
		
	}

	/**
	 * The same annotation can be applied to a declaration or type more than
	 * once, given that each annotation is marked as @Repeatable. In the
	 * following code, the @Repeatable annotation is used to develop an
	 * annotation that can be repeated, rather than grouped together as in
	 * previous releases of Java. In this situation, an annotation named Role is
	 * being created, and it will be used to signify a role for an annotated
	 * class or method.
	 * 
	 * @author doctor
	 *
	 * @since 2015年2月3日 下午8:51:09
	 */
	@Repeatable(value = Roles.class)
	public static @interface Role {
		String name() default "doctor";
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Roles {
		Role[] value();
	}
	
	@Role(name = "doctor")
	@Role(name = "who")
	public static class RepeatAnn{
		
	}
	
	@Roles({@Role(name="doctor"),
			@Role(name="who")})
	public static class Annotations{
		
	}
}

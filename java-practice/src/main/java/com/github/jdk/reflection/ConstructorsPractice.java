package com.github.jdk.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import com.google.common.base.Preconditions;

/**
 * @see http://tutorials.jenkov.com/java-reflection/constructors.html
 * @author doctor
 *
 * @since 2015年2月14日 下午3:22:36
 */
public class ConstructorsPractice {

	/**
	 * @param args
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws ReflectiveOperationException
	 * @throws IllegalArgumentException
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ReflectiveOperationException, IllegalArgumentException {

		// for each public constructor declared in the class.
		Arrays.stream(ConstructorsClass.class.getConstructors()).forEach(System.out::println);

		// If you know the precise parameter types of the constructor you want to access, you can do so rather than obtain the array all constructors.
		Constructor<ConstructorsClass> constructor = ConstructorsClass.class.getConstructor(new Class[] { String.class });
		System.out.println(constructor);

		Preconditions.checkArgument(Modifier.isPublic(constructor.getModifiers()));

		// You can read what parameters a given constructor takes like this:
		Arrays.stream(constructor.getParameters()).forEach(p -> {
			System.out.println(p.getType());
		});

		// You can instantiate an object like this
		ConstructorsClass newInstance = constructor.newInstance("doctor who");
		System.out.println(newInstance.getName());
	}

	public static class ConstructorsClass {
		private String name;

		public ConstructorsClass() {
			this("");
		}

		public ConstructorsClass(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

}

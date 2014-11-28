package com.github.ebook.javarefectioninaction;

/**
 * This small example is more dramatic than it seems—it contains each of the
 * steps previously mentioned. The printName method examines the object for its
 * class (this.getClass()). In doing so, the decision of what to print is made by del-
 * egating to the object’s class. The method acts on this decision by printing the
 * returned name. Without being overridden, the printName method behaves differ-
 * ently for each subclass than it does for HelloWorld. The printName method is flex-
 * ible; it adapts to the class that inherits it, causing the change in behavior. As we
 * build our examples in scope and complexity, we will show you many more ways to
 * attain flexibility using reflection.
 * 
 * 继承体系下除了overridden 技术外使得子类与父类表现出不同行为外，反射也可以,而且反射使得继承不需要overridden。
 * 
 * @author doctor
 *
 * @time 2014年11月28日 上午8:54:05
 */

public class GetClassPractice {

	public static void main(String[] args) {
		new HelloWorld().printName();
		new HelloWorldChild1().printName();
		new HelloWorldChild2().printName();
		
		System.out.println(new HelloWorld().getClassType());
		System.out.println(new HelloWorldChild1().getClassType());
		System.out.println(new HelloWorldChild2().getClassType());
		
		System.out.println("接口");
		new Hello1().printName();
		new Hello2().printName();
		
		System.out.println(Hello.class.isAssignableFrom(Hello1.class));
		System.out.println(HelloWorld.class.isAssignableFrom(HelloWorld.class));
		System.out.println(HelloWorld.class.isAssignableFrom(HelloWorldChild2.class)); 
		
		System.out.println(Hello.class.isInstance(new Hello1()));
		System.out.println(Hello.class.isInstance(new Hello2()));
		System.out.println(Hello.class.isInstance(new HelloWorldChild1()));
		

	}

	public static class HelloWorld {
		public void printName() {
			System.out.println(getClass().getName());
		}
		
		public  Class<? extends HelloWorld> getClassType(){
			return getClass();
		}
	}

	public static class HelloWorldChild1 extends HelloWorld {

	}

	public static class HelloWorldChild2 extends HelloWorld {

	}

	public interface Hello{
		public default void printName(){
			System.out.println(getClass().getName());
		}
	}
	
	public static class Hello1 implements Hello{
		
	}
	public static class Hello2 implements Hello{
		
	}
}

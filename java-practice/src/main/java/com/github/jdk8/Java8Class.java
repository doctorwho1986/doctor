package com.github.jdk8;

/**
 * We know that Java doesn’t allow us to extend multiple classes because it will
 * result in the “Diamond Problem” where compiler can’t decide which superclass
 * method to use. With the default methods, the diamond problem would arise for
 * interfaces too. Because if a class is implementing both Interface1 and
 * Interface2 and doesn’t implement the common default method, compiler can’t
 * decide which one to chose
 * 
 * Extending multiple interfaces are an integral part of Java, you will find it
 * in the core java classes as well as in most of the enterprise application and
 * frameworks. So to make sure, this problem won’t occur in interfaces, it’s
 * made mandatory to provide implementation for common default methods. So if a
 * class is implementing both the above interfaces, it will have to provide
 * implementation for log() method otherwise compiler will throw error.
 * 
 * 不覆写default 方法，编译器提示： Duplicate default methods named log with the parameters
 * (String) and (String) are inherited from the types Java8Interface2 and
 * Java8Interface1
 * 
 * @author doctor
 *
 * @since 2014年12月27日 上午11:23:45
 */
public class Java8Class implements Java8Interface1, Java8Interface2 {

	@Override
	public void method2() {
		System.out.println("method2");
	}

	@Override
	public void method1() {
		System.out.println("method1");
	}

	public void log(String str) {
		System.out.println("Java8Class log " + str);
	}
}

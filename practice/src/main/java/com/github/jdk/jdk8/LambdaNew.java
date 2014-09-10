package com.github.jdk.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LambdaNew {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("11","ab","de","abc","abcd");
		System.out.println(list);
		
		Collections.sort(list, (a,  b) -> a.compareTo(b));
//		Collections.sort(list, (String a, String b) -> a.compareTo(b));
		
		System.out.println(list);
		
//		1. Lambda语法
//
//		包含三个部分
//
//		一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数
//
//		一个箭头符号：->
//
//		方法体，可以是表达式和代码块，方法体函数式接口里面方法的实现，如果是代码块，则必须用{}来包裹起来，且需要一个return 返回值，但有个例外，若函数式接口里面方法返回值是void，则无需{}
//
//		总体看起来像这样

//		(parameters) -> expression 或者 (parameters) -> { statements; } 
		new Thread(() -> { System.out.println(Thread.currentThread().getName() + "started");}).start();
		
//		2.方法引用
//
//		其实是lambda表达式的一个简化写法，所引用的方法其实是lambda表达式的方法体实现，语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，右边是相应的方法名。如下所示：
//
//		    ObjectReference::methodName 
//
//		一般方法的引用格式是
//
//		    如果是静态方法，则是ClassName::methodName。如 Object ::equals
//		    如果是实例方法，则是Instance::methodName。如Object obj=new Object();obj::equals;
//		    构造函数.则是ClassName::new

		new Thread(LambdaNew::doSomething ).start();
		
		list.forEach(p -> System.out.println(p));
	
	}
	
	public static void doSomething( ){
		System.out.println("do something start...");
		String message = "dddddddddddddddddddd" ;
		System.out.println(message + Thread.currentThread().getName());
		System.out.println("do something end...");

	}

}

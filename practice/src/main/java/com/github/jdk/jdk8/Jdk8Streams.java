package com.github.jdk.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author see
 *         http://www.oracle.com/technetwork/articles/java/ma14-java-se-8-streams
 *         -2177646.html
 *
 */
public class Jdk8Streams {

	public static void main(String[] args) {
		list1();

		streams();

		stream转换();
	}

	/*
	 * Lambda语法详解
	 * 
	 * 我们在此抽象一下lambda表达式的一般语法：
	 * 
	 * (Type1 param1, Type2 param2, ..., TypeN paramN) -> { statment1;
	 * statment2; //............. return statmentM; }
	 * 
	 * 从lambda表达式的一般语法可以看出来，还是挺符合上面给出的非精确版本的定义–“一段带有输入参数的可执行语句块”。
	 * 
	 * 上面的lambda表达式语法可以认为是最全的版本，写起来还是稍稍有些繁琐。别着急，下面陆续介绍一下lambda表达式的各种简化版：
	 * 
	 * 1. 参数类型省略–绝大多数情况，编译器都可以从上下文环境中推断出lambda表达式的参数类型。这样lambda表达式就变成了：
	 * 
	 * (param1,param2, ..., paramN) -> { statment1; statment2; //.............
	 * return statmentM; } 2. 当lambda表达式的参数个数只有一个，可以省略小括号。lambda表达式简写为：
	 * 
	 * param1 -> { statment1; statment2; //............. return statmentM; } 3.
	 * 当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。lambda表达式简化为：
	 * 
	 * param1 -> statment
	 */
	public static void list1() {
		List<String> list = new ArrayList<>();
		list.add("doctor");
		list.add("name");
		list.add("who");
		list.add("doctor who");
		System.out.println(list);

		Collections.sort(list, (a, b) -> a.compareTo(b));
		System.out.println(list);

		/*
		 * see http://ifeve.com/lambda/
		 * 对一个字符串的列表，把其中包含的每个字符串都转换成全小写的字符串（熟悉Groovy和Scala的同学肯定会感觉很亲切）。
		 * 注意代码第四行的map方法调用，这里map方法就是接受了一个lambda表达式
		 * （其实是一个java.util.function.Function的实例，后面会介绍）。
		 */
		List<String> list2 = list.parallelStream().map(a -> a.toUpperCase()).collect(Collectors.toList());
		System.out.println(list2);
	}

	/*
	 * see http://ifeve.com/stream/ 1.Stream的基本步骤：
	 * 
	 * 创建Stream； 转换Stream，每次转换原有Stream对象不改变，返回一个新的Stream对象（**可以有多次转换**）；
	 * 对Stream进行聚合（Reduce）操作，获取想要的结果；
	 */
	public static void streams() {
		/*
		 * 2. 创建Stream
		 * 
		 * 最常用的创建Stream有两种途径：
		 * 
		 * 1.通过Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；of方法
		 * ,generator方法：生成一个无限长度的Stream，其元素的生成是通过给定的Supplier
		 * （这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象 * ）,
		 * terate方法：也是生成无限长度的Stream，和generator不同的是，其元素的生成是重复
		 * 对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环.
		 * 千万记住使用limit方法，不然会无限打印下去。 2.通过Collection接口的默认方法（默认方法：Default
		 * method，也是Java8中的一个新特性，就是接口中的一个带有实现的方法，后续文章会有介绍）–stream()
		 * ，把一个Collection对象转换成Stream
		 */
		Stream<Integer> streamInteger = Stream.of(1, 33, 5, 66, 88, 77);
		streamInteger.forEach(t -> System.out.print(t + "  "));
		System.out.println();

		Stream<Double> streamDouble = Stream.generate(Math::random);
		streamDouble.limit(20).forEach(t -> System.out.print(t + "  "));// 千万记住使用limit方法，不然会无限打印下去。
		System.out.println();

	}

	/*
	 * see http://ifeve.com/stream/ 3. 转换Stream
	 * 
	 * 转换Stream其实就是把一个Stream通过某些行为转换成一个新的Stream。Stream接口中定义了几个常用的转换方法，
	 * 下面几个常用的转换方法来解 释。 1. distinct:
	 * 对于Stream中包含的元素进行去重操作（去重逻辑依赖元素的equals方法），新生成的Stream中没有重复的元素；
	 * 
	 * 
	 * 2. filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素；
	 * 
	 * 
	 * 3. map:
	 * 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。这个方法有三个对于原始类型的变种方法
	 * ，分别是：mapToInt，mapToLong和mapToDouble。这三个方法也比较好理解，
	 * 比如mapToInt就是把原始Stream转换成一个新的Stream
	 * ，这个新生成的Stream中的元素都是int类型。之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗；
	 * 
	 * 
	 * 4. flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中；
	 * 
	 * 
	 * 5. peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），
	 * 新Stream每个元素被消费的时候都会执行给定的消费函数；
	 * 
	 * 
	 * 
	 * 6. limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
	 * 
	 * 
	 * 
	 * 7. skip:
	 * 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
	 */

	public static void stream转换() {
		List<Integer> list = Arrays.asList(1, 2, 44, 55, 77, 34, null, 88, null, 66, 88, 22, 1, 0, null);
		int sum = list.parallelStream().filter(t -> null != t).distinct().mapToInt(t -> t << 1).peek(System.out::println).
				skip(2).limit(6).sum();
		System.out.println(sum);

		/*
		 * 性能问题
		 * 有些细心的同学可能会有这样的疑问：在对于一个Stream进行多次转换操作，每次都对Stream的每个元素进行转换，而且是执行多次，
		 * 这样时间复杂度就是一个for循环里把所有操作都做掉的N
		 * （转换的次数）倍啊。其实不是这样的，转换操作都是lazy的，多个转换操作只会在汇聚操作
		 * （见下节）的时候融合起来，一次循环完成。我们可以这样简单的理解
		 * ，Stream里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，
		 * 在汇聚操作的时候循环Stream对应的集合，然后对每个元素执行所有的函数。
		 */
	}

	/*
	 * 汇聚（Reduce）Stream
	 * 汇聚操作（也称为折叠）接受一个元素序列为输入，反复使用某个合并操作，把序列中的元素合并成一个汇总的结果。比如查找一个数字列表的总和或者最大值
	 * ，或者把这些数字累积成一个List对象
	 * 。Stream接口有一些通用的汇聚操作，比如reduce()和collect()；也有一些特定用途的汇聚操作，
	 * 比如sum(),max()和count
	 * ()。注意：sum方法不是所有的Stream对象都有的，只有IntStream、LongStream和DoubleStream是实例才有。
	 * 
	 * 下面会分两部分来介绍汇聚操作：
	 * 
	 * 可变汇聚：把输入的元素们累积到一个可变的容器中，比如Collection或者StringBuilder；
	 * 其他汇聚：除去可变汇聚剩下的，一般都不是通过反复修改某个可变对象
	 * ，而是通过把前一次的汇聚结果当成下一次的入参，反复如此。比如reduce，count，allMatch；
	 */

	public static void ReduceStream() {

	}

}

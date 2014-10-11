package com.github.jdk.java_util_concurrent;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author
 * @link http://markusjais.com/java-concurrency-understanding-copyonwritearraylist-and-copyonwritearrayset/
 *
 *       Two sometimes very useful classes are the CopyOnWriteArrayList and CopyOnWriteArraySet.
 * 
 *       They implement the java.util.List and the java.util.Set interface respectively.
 * 
 *       Let’s focus on the CopyOnWriteArrayList to understand what it is all about.
 * 
 *       Contrary to the ArrayList, this class is thread safe. This means when you use it
 * 
 *       from several threads no undefined state can occur in the list.
 * 
 *       As will all data structures it is important to understand when to use them.
 * 
 *       As the name CopyOnWrite says, a copy of the whole list is made each time you write to
 * 
 *       the list like adding an element or remove an element. As you can figure out yourself,
 * 
 *       this can be pretty expensive when your list is large.
 * 
 *       This means that a CopyOnWriteArrayList (and CopyOnWriteArraySet) is mostly useful
 * 
 *       when you have few modifications but many reads because reads are very cheap
 * 
 *       and don’t require synchronization.
 * 
 *       When you iterate over a CopyOnWriteArrayList and CopyOnWriteArraySet the iterator
 * 
 *       uses a snapshot of the underlying list (or set) and does not reflect any changes
 * 
 *       to the list or set after the snapshot was created.
 * 
 *       The iterator will never throw a ConcurrentModificationException.
 */
public class CopyOnWriteArrayListPractice {

	public static void main(String[] args) {
		test_copyOnWriteArrayList();
	}

	private static void test_copyOnWriteArrayList() {
		CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		ExecutorService threadExecutor = Executors.newFixedThreadPool(5);
		threadExecutor.submit(() -> {
			try {
				TimeUnit.MICROSECONDS.sleep(25);
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

			copyOnWriteArrayList.add(10);
			System.out.println(copyOnWriteArrayList);
		});

		threadExecutor.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				;
			} catch (Exception e) {
				e.printStackTrace();
			}

			copyOnWriteArrayList.add(12);
			System.out.println(copyOnWriteArrayList);
		});

		threadExecutor.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(copyOnWriteArrayList);
		});

		threadExecutor.shutdown();

		for (Integer integer : copyOnWriteArrayList) {
			System.out.println(integer);
		}

		/**
		 * 1
		 * 2
		 * 3
		 * 4
		 * [1, 2, 3, 4, 5, 10]
		 * 5
		 * [1, 2, 3, 4, 5, 10]
		 * [1, 2, 3, 4, 5, 10, 12]
		 * 
		 * As you can see the for loop only prints the numbers 1-5 and the number 10 is
		 * 
		 * not printed in the for loop as it was not present when the snapshot of the iterator was taken.
		 * 
		 * Conclusion:
		 * 
		 * CopyOnWriteArrayList and CopyOnWriteArraySet (which is implemented with a CopyOnWriteArrayList)
		 * are special data structures for use cases where you want to share the data structure
		 * among several threads and have few writes and many reads.
		 * Always make sure to do a performance test for your code on real hardware to
		 * see how it performs in your application. And make sure to read the javadoc for
		 * all the methods to really understand how the data structures work.
		 * Of course you can also use CopyOnWriteArrayList and CopyOnWriteArraySet from
		 * other JVM languages like Scala, Clojure, JRuby or Groovy.
		 * 
		 * Immutable collections.
		 * Sometimes you just need to create the list or set once and then later onl
		 * y read from it. In this case I recommend having a look at the immutable
		 * collections from Guava.. They are always thread safe (as is every really
		 * immutable object) and are a better alternative to the wrapped immutable
		 * collections that come with the JDK. See the Guava website for why that is the case.
		 */
	}
}

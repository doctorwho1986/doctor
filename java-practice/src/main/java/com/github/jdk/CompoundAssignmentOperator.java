package com.github.jdk;

/**
 * @see http//howtodoinjava.com/2014/06/05/compound-assignment-operator-i-j-is-not-same-as-i-i-j-in- java/
 * @author doctor
 *
 * @since 2015年2月15日 下午9:39:37
 */
public class CompoundAssignmentOperator {

	/**
	 * We all have used syntax’s like i += j and i = i + j thousands of times in our day to day programming. In first sight, they
	 * both look similar. In fact, they will result in same output in almost all of the cases in practical cases. But, to surprise
	 * you they are not similar. In run-time, they are treated differently when i and j are of different types.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 5;
		double d = (double) i + 4.5;
		i += 4.5;
		System.out.println(d);
		System.out.println(i);

		// A compound assignment expression of the form E1 op= E2 is equivalent to E1 = (T)((E1) op
		// (E2)), where T is the type of E1, except that E1 is evaluated only once.
		// So effectively our original example code can be re-written as below:
		int j = 5;
		double d1 = (double) j + 4.5;
		j = (int) (j + 4.5);
		System.out.println(d1);
		System.out.println(j);
	}
}

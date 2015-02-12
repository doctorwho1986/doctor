package com.github.jdk;

/**
 * @see http://examples.javacodegeeks.com/java-basics/data-types/enum/each-enum-instance-a-different-sub-class/
 * @author doctor
 *
 */
public class EachEnumInstanceADifferentSubClass {

	public static void main(String[] args) {
		double operate = Operation.puls.operate(12.5, 12.45);
		System.out.println(operate);
		
		operate = Operation.minus.operate(15.9, 12.9);
		System.out.println(operate);
	}

	/**
	 * In this example we shall show you how to have each enum instance 
	 * 
	 * represent a different sub-class. 
	 * 
	 * To make each enum instance represent a different sub-class one should 
	 * 
	 * perform the following steps:
	 * 
	 * 1.Create an enum with different enum constants.
	 * 2.Give each enum constant a different behavior for some method.
	 * 3.Declare the method abstract in the enum type and override it with a concrete method in each constant. 
	 * 
	 * Such methods are known as constant-specific methods,
	 * 
	 * as described in the code snippet below.
	 * 
	 * @author
	 *
	 */
	public static enum Operation {
		puls{
			
			@Override
			double operate(double x, double y) {
				return x+y;
			}
			
		},
		minus{

			@Override
			double operate(double x, double y) {
				return  x-y;
			}
			
		};
		
		
		abstract double operate(double x,double y);
	}

}

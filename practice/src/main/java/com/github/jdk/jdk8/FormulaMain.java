package com.github.jdk.jdk8;

import junit.framework.Assert;




public class FormulaMain {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Formula formula = new Formula(){

			@Override
			public double add(double a, double b) {
				return a+b;
			}
			
		};
		
		Assert.assertEquals((double)5, formula.sqrt(25));
	}

}

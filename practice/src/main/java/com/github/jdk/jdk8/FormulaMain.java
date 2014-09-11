package com.github.jdk.jdk8;






public class FormulaMain {

	public static void main(String[] args) {
		
		Formula formula = new Formula(){

			@Override
			public double add(double a, double b) {
				return a+b;
			}
			
		};
		
		System.out.println(formula.add(3, 5));
	}

}

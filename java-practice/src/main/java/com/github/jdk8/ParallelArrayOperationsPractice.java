package com.github.jdk8;

import java.util.Arrays;

public class ParallelArrayOperationsPractice {

	public static void main(String[] args) {
		int[] intArray = new int[256];
		Arrays.parallelSetAll(intArray, i -> i);
		for (int j : intArray) {
			System.out.println(j);
		}

	}

}

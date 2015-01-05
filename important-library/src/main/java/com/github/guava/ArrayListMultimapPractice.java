package com.github.guava;

import java.util.Collection;
import java.util.Map.Entry;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

public class ArrayListMultimapPractice {

	public static void main(String[] args) {
		ListMultimap<String, Integer> multimap = ArrayListMultimap.create();
		multimap.put("1", 1);
		multimap.put("1", 2);
		multimap.put("3", 5);
		multimap.put("3", 15);

		for (Entry<String, Collection<Integer>> entry : multimap.asMap().entrySet()) {
			System.out.print(entry.getKey() + ":");
			for (Integer value : entry.getValue()) {
				System.out.print(value + "  ");
			}
			System.out.println();
		}

	}

}

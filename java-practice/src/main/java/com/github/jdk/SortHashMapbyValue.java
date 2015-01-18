package com.github.jdk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * SortHashMapbyValue
 * 
 * @author doctor
 *
 * @since 2015年1月3日 下午4:37:51
 */
public class SortHashMapbyValue {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("b", 13);
		map.put("a", 36);
		map.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});

		ValueComparator valueComparator = new ValueComparator(map);

		Map<String, Integer> treeMap = new TreeMap<>(valueComparator);
		treeMap.putAll(map);
		treeMap.forEach((k, v) -> {
			System.out.println(k + " : " + v);
		});

	}

	private static class ValueComparator implements Comparator<String> {
		private Map<String, Integer> map;

		public ValueComparator(Map<String, Integer> map) {
			this.map = map;
		}

		@Override
		public int compare(String o1, String o2) {
			if (map.get(o1) >= map.get(o2)) {
				return 1;
			}
			return -1;
		}

	}
}

package com.doctor.java.algorithm;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.google.common.collect.Lists;

public class Simhash2 {
	private List<String> input = Lists.newArrayList("name=doctor", "age=12", "sex=男");
	private List<List<String>> allList = Lists.newLinkedList();
	
	@Before
	public void init(){
		
		allList.add(Lists.newArrayList("name=doct", "age=12", "sex=女"));
		allList.add(Lists.newArrayList("age=12", "name=doctor", "sex=男"));
		allList.add(Lists.newArrayList("age=15", "name=doctor", "sex=女"));
		allList.add(Lists.newArrayList("age=18", "name=doctorw", "sex=男"));
		allList.add(Lists.newArrayList("age<=12", "name=dodctwor", "sex=女"));
		allList.add(Lists.newArrayList("age>12", "name=doctor", "sex=男"));
		allList.add(Lists.newArrayList("age=12", "name=doctor", "sex=男"));
		
		
	}
	
	@Test
	public void test_() {
		
		 
	}

	public int hammingDistance(List<String> list1,List<String> list2){
		
		return hammingDistance(simhash64(list1), simhash64(list2));
		
	}
	public int hammingDistance(long hash1, long hash2) {
		long i = hash1 ^ hash2;
		i = i - ((i >>> 1) & 0x5555555555555555L);
		i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
		i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		i = i + (i >>> 32);
		return (int) i & 0x7f;
	}

	public long simhash64(List<String> list) {
		int bitLen = 64;
		int[] bits = new int[bitLen];

		for (String t : list) {
			long v = MurmurHash.hash64(t);
			for (int i = bitLen; i >= 1; --i) {
				if (((v >> (bitLen - i)) & 1) == 1)
					++bits[i - 1];
				else
					--bits[i - 1];
			}
		}
		long hash = 0x0000000000000000;
		long one = 0x0000000000000001;
		for (int i = bitLen; i >= 1; --i) {
			if (bits[i - 1] > 1) {
				hash |= one;
			}
			one = one << 1;
		}
		return hash;
	}
}

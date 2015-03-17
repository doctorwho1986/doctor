package com.doctor.java.algorithm;

import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * simhash算法
 * 
 * @author doctor
 *
 * @time 2015年3月17日
 */
public class SimHashPractice {

	@Test
	public void test_() {
		List<String> list1 = Lists.newArrayList("name=doctor", "age=12", "sex=man");
		List<String> list2 = Lists.newArrayList("name=doctor", "age=12", "sex=mans");
		System.out.println(hammingDistance(list1, list2));
	}

	private BigInteger hash(String source) {
		if (source == null || source.length() == 0) {
			return new BigInteger("0");
		} else {
			char[] sourceArray = source.toCharArray();
			BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
			BigInteger m = new BigInteger("1000003");
			BigInteger mask = new BigInteger("2").pow(128).subtract(new BigInteger("1"));
			for (char item : sourceArray) {
				BigInteger temp = BigInteger.valueOf((long) item);
				x = x.multiply(m).xor(temp).and(mask);
			}
			x = x.xor(new BigInteger(String.valueOf(source.length())));
			if (x.equals(new BigInteger("-1"))) {
				x = new BigInteger("-2");
			}
			return x;
		}
	}

	private BigInteger simHash(List<String> list) {
		int[] bits = new int[128];
		list.forEach(t -> {
			BigInteger hash = hash(t);
			for (int i = 0; i < bits.length; i++) {
				BigInteger bitmask = new BigInteger("1").shiftLeft(i);
				if (hash.and(bitmask).signum() != 0) {
					bits[i] += 1;
				} else {
					bits[i] -= 1;
				}
			}
		});

		BigInteger fingerprint = new BigInteger("0");
		for (int i = 0; i < bits.length; i++) {
			if (bits[i] > 0) {
				fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
			}
		}
		return fingerprint;

	}

	private int hammingDistance(List<String> list1, List<String> list2) {
		BigInteger m = new BigInteger("1").shiftLeft(128).subtract(new BigInteger("1"));
		BigInteger x = simHash(list1).xor(simHash(list2)).and(m);
		int tot = 0;
		while (x.signum() != 0) {
			tot += 1;
			x = x.and(x.subtract(new BigInteger("1")));
		}
		return tot;
	}
}

package com.github.guava;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.google.common.primitives.Longs;

public class LongsPractice {

	public static void main(String[] args) {
		List<Long> asList = Longs.asList(12L,13L);
		Assert.assertEquals(Arrays.asList(12L,13L),asList);
		
		Assert.assertEquals(-1, Longs.compare(12L, 13L));
		Assert.assertEquals(0, Longs.compare(13L, 13L));
		Assert.assertEquals(1, Longs.compare(13L, 12L));
		
		long[] concat = Longs.concat(new long[]{12L,14L},new long[]{15L,16L});
		boolean equals = Arrays.equals(new long[]{12L,14L,15L,16L}, concat);
		Assert.assertTrue(equals);
		
		byte[] bytes = new byte[]{0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19};
		long fromByteArray = Longs.fromByteArray(bytes );
		long longByte = 0x1213141516171819L;
		Assert.assertEquals(longByte, fromByteArray);
		
		Arrays.equals(bytes,Longs.toByteArray(longByte));
		
		Assert.assertEquals(1, Long.bitCount(2L));
		Assert.assertEquals(63, Long.bitCount(Long.MAX_VALUE));
		Assert.assertEquals(Long.toBinaryString(255L).length(), Long.bitCount(255L));
		Assert.assertEquals(Long.toBinaryString(Long.MAX_VALUE).length(), Long.bitCount(Long.MAX_VALUE));
		
		Assert.assertEquals(234L, Long.decode("234").longValue());
		Assert.assertEquals(564L, Long.decode("0x234").longValue());
		System.out.println(0x234L);
		
		Long hexlLong = 0x12356L;
		String hexString = Long.toHexString(hexlLong);
		Assert.assertEquals(Long.toString(hexlLong, 16), hexString);

	}

}

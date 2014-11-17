package com.doctor.beanutil;

import org.apache.commons.beanutils.converters.IntegerConverter;

import com.google.common.base.Preconditions;

public class IntegerConverterPractice {

	public static void main(String[] args) {
		IntegerConverter integerConverter = new IntegerConverter();
		String convert = integerConverter.convert(String.class, null);
		System.out.println(convert);
		Preconditions.checkArgument(integerConverter.convert(String.class, "12").equals("12"));
		Preconditions.checkArgument(integerConverter.convert(Integer.class, "12").equals(12));
		Preconditions.checkArgument(integerConverter.convert(Long.class, "12").equals(12L));
		Preconditions.checkArgument(integerConverter.convert(Integer.class, "12").equals(12));
		Preconditions.checkArgument(integerConverter.convert(String.class, 12).equals("12"));

	}

}

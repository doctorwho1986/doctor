package com.doctor.beanutil;

import org.apache.commons.beanutils.converters.StringConverter;

import com.google.common.base.Preconditions;

public class StringConverterPractice {

	public static void main(String[] args) {
		StringConverter stringConverter = new StringConverter("empty");
		System.out.println(stringConverter.convert(String.class, null));
		Preconditions.checkArgument(stringConverter.convert(String.class, null)==null);
		Preconditions.checkArgument(stringConverter.convert(String.class, "ss").equals("ss"));
		Preconditions.checkArgument(stringConverter.convert(String.class, 12).equals("12"));
		Preconditions.checkArgument(stringConverter.convert(String.class, 12.000F).equals("12.0"));
		Preconditions.checkArgument(stringConverter.convert(String.class, 12.001F).equals("12.001"));
		
		try {
			Preconditions.checkArgument(stringConverter.convert(Integer.class, "12").equals(Integer.valueOf(12)));
		} catch (Exception e) {
			 
			System.out.println(e.getMessage());
		}
	}

}

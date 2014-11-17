package com.doctor.beanutil;

import org.apache.commons.beanutils.converters.StringConverter;

import com.google.common.base.Preconditions;

public class StringConverterPractice {

	public static void main(String[] args) {
		StringConverter stringConverter = new StringConverter("empty");
		System.out.println(stringConverter.convert(String.class, null));
		Preconditions.checkArgument(stringConverter.convert(String.class, null)==null);
		Preconditions.checkArgument(stringConverter.convert(String.class, "ss").equals("ss"));
	}

}

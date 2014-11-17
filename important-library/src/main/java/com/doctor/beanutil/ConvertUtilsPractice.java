package com.doctor.beanutil;

import java.time.LocalDateTime;

import org.apache.commons.beanutils.ConvertUtils;

import com.google.common.base.Preconditions;

public class ConvertUtilsPractice {

	public static void main(String[] args) {
		String convert = ConvertUtils.convert(LocalDateTime.now());
		System.out.println(convert);
		Integer convert2 = (Integer) ConvertUtils.convert("12", Integer.class);
		System.out.println(convert2);

		
		try {
			Object convert3 = ConvertUtils.convert(LocalDateTime.now().toString(), LocalDateTime.class);
			System.out.println(convert3);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		ConvertUtils.register(new ApidocsPractice.LocalDateTimeConverter(), LocalDateTime.class);
		LocalDateTime convert3 = (LocalDateTime) ConvertUtils.convert(LocalDateTime.now().toString(), LocalDateTime.class);
		System.out.println(convert3);
		
		Long convert4 = (Long) ConvertUtils.convert(12L, Long.class);
		Preconditions.checkArgument(convert4.compareTo(12L) == 0);
		
	
	}

}

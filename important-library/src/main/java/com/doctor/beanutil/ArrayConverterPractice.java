package com.doctor.beanutil;

import java.util.ArrayList;

import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;

public class ArrayConverterPractice {

	public static void main(String[] args) {
		ArrayConverter arrayConverter = new ArrayConverter(Integer[].class, new IntegerConverter());
		Integer[] convert = arrayConverter.convert(Integer[].class, "1,2,3,4,5,6");
		for (Integer integer : convert) {
			System.out.print(integer + "    ");
		}
		arrayConverter.setDelimiter('|');
		Double[] convert2 = arrayConverter.convert(Double[].class, "12.01|34.56|56.45|88.90");
		System.out.println();
		for (Double long1 : convert2) {
			System.out.print(long1 + " ");
		}
		
		try {
			ArrayList<Double> arrayList = arrayConverter.convert(ArrayList.class, "12.01|34.56|56.45|88.90");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}

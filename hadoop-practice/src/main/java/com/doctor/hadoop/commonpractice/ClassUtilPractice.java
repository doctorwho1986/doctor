package com.doctor.hadoop.commonpractice;

import java.io.File;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.util.ClassUtil;
import org.slf4j.Logger;

public class ClassUtilPractice {

	public static void main(String[] args) {
		String findContainingJar = ClassUtil.findContainingJar(Logger.class);
		System.out.println(findContainingJar);
		
		Objects.nonNull(findContainingJar);
		
		File file = FileUtils.getFile(findContainingJar);
		Objects.nonNull(file);
		System.out.println(file);
		Objects.equals("slf4j-api-1.7.7", file.getName());
		System.out.println(file.getName());

	}

}

package com.github.jdkcommon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author doctor
 *
 * @since 2014年12月18日 上午12:16:46
 */
public class LoadResource {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream inputStream = LoadResource.class.getClassLoader().getResourceAsStream("javaPracticeProp/propertyPractice.properties");
		Properties properties = new Properties();
		properties.load(inputStream);
		System.out.println(properties);
		
		InputStream stream = LoadResource.class.getResourceAsStream("/practice.properties");
		Properties pro = new Properties();
		pro.load(stream);
		System.out.println(pro);

	}

}

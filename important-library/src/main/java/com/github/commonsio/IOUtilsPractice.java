package com.github.commonsio;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class IOUtilsPractice {

	public static void main(String[] args) {
		try (InputStream inputStream = new URL("http://commons.apache.org/proper/commons-io/description.html").openStream()) {
			String read = IOUtils.toString(inputStream);
			System.out.println(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("/////////////////////////////////////////");
		try {
			URL url = new URL("http://zguide.zeromq.org/");
			String read = IOUtils.toString(url, StandardCharsets.UTF_8);
			System.out.println(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}

package com.github.jdk;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLConnection;
/**
 * @see http://examples.javacodegeeks.com/core-java/net/cookiestore-net/java-net-cookiestore-example/
 * @author doctor
 *
 */
public class CookieStorePractice {
	private static final String url = "http://www.baidu.com/";

	public static void main(String[] args) throws IOException {
		CookieManager cookieManager = new CookieManager();
		CookieHandler.setDefault(cookieManager);
		
		URL netUrl = new URL(url);
		URLConnection urlConnection = netUrl.openConnection();
		Object content = urlConnection.getContent();
		System.out.println(content.toString());
		System.out.println("----------------------------------------");
		
		cookieManager.getCookieStore().getCookies().forEach((t) -> {
			System.out.println("Cookie name:" + t.getName());
			System.out.println("Cookie value:" + t.getValue());
			System.out.println("Cookie version:" + t.getVersion());
			System.out.println("Cookie domain:" + t.getDomain());
			System.out.println("Cookie maxAge:" + t.getMaxAge());
			System.out.println("Cookie path:" + t.getPath());
			System.out.println("Cookie secure:" + t.getSecure());
			System.out.println("--------------------------------");
		});

	}

}

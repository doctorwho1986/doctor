package com.github.netexample.server;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class HttpServerTest {

	@Test
	public void test() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpResponse httpResponse = httpClient.execute(new HttpGet("http://localhost:8080/index.html"));
		String content = httpResponse.getEntity().toString();
		System.out.println(content);
	}

}

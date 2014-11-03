package com.doctor.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class Chapter1 {

	@Test
	public void test_Request_execution() throws ClientProtocolException, IOException{
		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
		HttpResponse response = httpClient.execute(httpGet);
		System.out.println(response);
	}
}

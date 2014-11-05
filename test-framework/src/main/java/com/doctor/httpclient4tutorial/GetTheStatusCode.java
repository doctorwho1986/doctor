package com.doctor.httpclient4tutorial;

import java.io.IOException;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetTheStatusCode {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("http://www.baeldung.com/httpclient-status-code");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		assertThat(HttpStatus.SC_OK, equalTo(httpResponse.getStatusLine().getStatusCode()));
	}

}

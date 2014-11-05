package com.doctor.httpclient4tutorial;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetTheStatusCode {
	private HttpGet httpGet = new HttpGet("http://www.baeldung.com/httpclient-status-code");
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode() throws ClientProtocolException, IOException {
		// givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		assertThat(HttpStatus.SC_OK, equalTo(httpResponse.getStatusLine().getStatusCode()));
	}

	@Test
	public void configure_Timeouts_using_the_new_4_3_Builder() throws ClientProtocolException, IOException {
		int timeout = 1;
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();

		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
		
		expectedException.expect(ConnectTimeoutException.class);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println(httpResponse.getStatusLine().getStatusCode());
		
		
	}

	@Test
	public void how_to_send_a_Custom_Cookie() throws ClientProtocolException, IOException{
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie clientCookie = new BasicClientCookie("doctor", "who");
		clientCookie.setDomain(".baidu.com");
		clientCookie.setPath("/");
		cookieStore.addCookie(clientCookie);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
		
		
		List<Cookie> cookiesBefore = cookieStore.getCookies();
		System.out.println("cookiesBefore");
		for (Cookie cookie : cookiesBefore) {
			System.out.println(cookie.getName() + " : " + cookie.getValue());
		}
		
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	
		List<Cookie> cookies = cookieStore.getCookies();
		System.out.println("cookies after");
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " : " + cookie.getValue());
		}
	}
	
	@Test
	public void set_the_Cookie_on_the_Request() throws ClientProtocolException, IOException{
		BasicCookieStore cookieStore = new BasicCookieStore();
		BasicClientCookie clientCookie = new BasicClientCookie("doctor", "who");
		clientCookie.setDomain(".baidu.com");
		clientCookie.setPath("/");
		cookieStore.addCookie(clientCookie);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		HttpContext context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
		httpClient.execute(httpGet, context );
		
		List<Cookie> cookies = cookieStore.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " : " + cookie.getValue());
		}
	}
}

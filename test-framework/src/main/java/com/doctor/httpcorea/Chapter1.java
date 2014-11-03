package com.doctor.httpcorea;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.*;

/**
 * 
 * @author see http://hc.apache.org/httpcomponents-core-ga/tutorial/html/fundamentals.html
 *
 * @time   2014年11月3日 下午4:57:19
 */
public class Chapter1 {

	@Test
	public  void test_http_request() {
		HttpRequest httpRequest = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);
		
		assertThat("GET", is(httpRequest.getRequestLine().getMethod()));
		assertThat(HttpVersion.HTTP_1_1, is(httpRequest.getRequestLine().getProtocolVersion()));
		assertThat("/", is(httpRequest.getRequestLine().getUri()));
		assertThat(HttpVersion.HTTP_1_1, is(httpRequest.getProtocolVersion()));
		assertThat("GET / HTTP/1.1", is(httpRequest.getRequestLine().toString()));
	}

	
	@Test
	public void test_http_response(){
		HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		assertThat(HttpVersion.HTTP_1_1, is(httpResponse.getProtocolVersion()));
		assertThat(HttpVersion.HTTP_1_1, is(httpResponse.getStatusLine().getProtocolVersion()));
		assertThat(HttpStatus.SC_OK, is(httpResponse.getStatusLine().getStatusCode()));
		assertThat("OK", is(httpResponse.getStatusLine().getReasonPhrase()));
		assertThat("HTTP/1.1 200 OK", is(httpResponse.getStatusLine().toString()));
	}
}

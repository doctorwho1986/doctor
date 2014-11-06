package com.doctor.moco.apiexample;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.RequestBuilder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;

import com.github.dreamhead.moco.HttpProtocolVersion;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Moco;
import com.github.dreamhead.moco.Runner;

/**
 * 
 * @author see https://github.com/dreamhead/moco
 *
 * @time   2014年11月3日 上午11:40:25
 */
public class ApiExample {
	
	@Test
	public void test_should_response_as_expected() throws Exception{
		HttpServer httpServer = Moco.httpserver(12306);
		httpServer.response("doctor");
		
		Runner.running(httpServer, () -> {
			Content content = Request.Get("http://localhost:12306").execute().returnContent();
			assertThat(content.toString(), is("doctor"));
		});
	}
	
	@Test
	public void test_Composite_Java_API_Design() throws Exception{
		HttpServer httpserver = Moco.httpserver(12306);
//		httpserver.request(Moco.and(by(uri("/target")), by(version(VERSION_1_0)))).response(with(text("foo")), header("Content-Type", "text/html"));
		httpserver.request(Moco.and(
				Moco.by(Moco.uri("/a")
						))).response(Moco.with("hello-doctor-who"),
								Moco.header("who", "doctor"),
								Moco.cookie("no", "me"));
		Runner.running(httpserver, () -> {
			
			HttpResponse httpResponse = Request.Get("http://localhost:12306/a").execute().returnResponse();
			assertThat(HttpVersion.HTTP_1_1, equalTo(httpResponse.getProtocolVersion()));
			Header[] headers = httpResponse.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + " : " + header.getValue());
			}
			
		}); 
		
	
	}
	
	
}

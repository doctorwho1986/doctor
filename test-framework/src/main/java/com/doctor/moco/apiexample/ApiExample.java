package com.doctor.moco.apiexample;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import static org.junit.Assert.assertThat;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

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
}

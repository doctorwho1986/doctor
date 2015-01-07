package com.doctor.embeddedjetty;


import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

public class EmbeddedJettyServer4ForWebappTest {

	@Test
	public void test() throws Throwable{
		EmbeddedJettyServer4ForWebapp server = new EmbeddedJettyServer4ForWebapp(8789,"/embeddedJettyServer4ForWebapp");
		server.start();
		Response response = Request.Get("http://localhost:8789/jetty/test.html").execute();
		System.out.println(response.returnContent().asString());
		
		response = Request.Get("http://localhost:8789/jetty/test.json").execute();
		System.out.println(response.returnContent().asString());
		server.stop();
	}

}

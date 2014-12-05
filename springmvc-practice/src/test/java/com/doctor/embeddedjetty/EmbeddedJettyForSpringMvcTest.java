package com.doctor.embeddedjetty;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


public class EmbeddedJettyForSpringMvcTest {
	
	@Test
	public void test() throws Throwable{
		EmbeddedJettyServer jettyServer = new EmbeddedJettyServer(SpringConfiguration.class);
		jettyServer.start();
		
		Response response = Request.Get("http://localhost:8080/").execute();
		assertThat(response.returnContent().asString(), equalTo("hello"));
		jettyServer.stop();
	}
	
	
	@Configuration
	@ComponentScan("com.doctor.embeddedjetty")
	static class SpringConfiguration{
		
	}
}

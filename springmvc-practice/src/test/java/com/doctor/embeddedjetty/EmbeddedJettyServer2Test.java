package com.doctor.embeddedjetty;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 标准spring 配置（java config） 嵌入式jetty9启动 测试
 * 
 * @author doctor
 *
 * @time   2014年12月8日 下午4:06:41
 */
public class EmbeddedJettyServer2Test {

	@Test
	public void test() throws Throwable{
		EmbeddedJettyServer2 embeddedJettyServer = new EmbeddedJettyServer2(SpringRootConfiguration.class, SpringMvcConfiguration.class);
		embeddedJettyServer.start();
		
		Response response = Request.Get("http://localhost:8080/embeddedJettyServer2Test").execute();
		assertThat(response.returnContent().asString(), equalTo("SimpleServiceImpl"));
		embeddedJettyServer.stop();
	}
	
	@Configuration
	@ComponentScan("com.doctor.embeddedjetty")
	public static class SpringRootConfiguration{
		
	}
	
	@Configuration
	@ComponentScan("com.doctor.embeddedjetty")
	public static class SpringMvcConfiguration{
		
	}

}

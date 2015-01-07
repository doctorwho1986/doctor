package com.doctor.embeddedjetty;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmbeddedJettyServer4ForWebappTest {

	@Test
	public void test() throws Throwable{
		EmbeddedJettyServer4ForWebapp server = new EmbeddedJettyServer4ForWebapp(8789,"/embeddedJettyServer4ForWebapp");
		server.start();
		while(true){
			
		}
	}

}

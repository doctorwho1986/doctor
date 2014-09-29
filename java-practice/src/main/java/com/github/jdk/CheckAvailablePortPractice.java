package com.github.jdk;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckAvailablePortPractice {
	private static final Logger log = LoggerFactory.getLogger(CheckAvailablePortPractice.class);
	public static void main(String[] args) throws IOException {
		System.out.println(checkAvailablePort(80));
	}
	
	private static int checkAvailablePort(int port){
		
		for(int i = port; i != 65500; i++){
			try (ServerSocket serverSocket = new ServerSocket(i)){
				return i;
			} catch (IOException e) {
				log.info("{port not available: '{}'}",i);
			}
		}
		
		throw new IllegalArgumentException("port :" + port + " too little ");		
	}
}

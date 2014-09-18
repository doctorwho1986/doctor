package com.github.zeromq.zguide;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;


public class Chapter1HelloWord {

	@Test
	public void hwserver(){
		ZMQ.Context context = ZMQ.context(1);
		//serverSocket to talk to clients
		ZMQ.Socket serverSocket = context.socket(ZMQ.REP);
		serverSocket.bind("tcp://*:8888");
		try {
			while (!Thread.currentThread().isInterrupted()) {
				// Wait for next request from the client
				byte[] recv = serverSocket.recv(0);
				System.out.println("Server Received :"+ new String(recv));
				
				TimeUnit.SECONDS.sleep(2);
				
				serverSocket.send("World",0);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			serverSocket.close();
			context.term();
		}
	}
	
	@Test
	public void hwclient(){
		ZMQ.Context context = ZMQ.context(1);
		Socket clientsSocket = context.socket(ZMQ.REQ);
		System.out.println("Connecting... the server...");
		clientsSocket.connect("tcp://localhost:8888");
		
		String helloMessage = "Hello";
		for(int i= 0; i< 10; i++){
			clientsSocket.send(helloMessage, 0);
			System.out.println("client send :" + helloMessage);
			
			byte[] recv = clientsSocket.recv(0);
			System.out.println("client received: " + new String(recv));
		}
		
		clientsSocket.close();
		context.term();
	}

	
	@Test
	public void ØMQ_Version(){
		System.out.println("zmq version : " + ZMQ.getVersionString() + ". getFullVersion :" +  ZMQ.getFullVersion());
		
	}
	
}

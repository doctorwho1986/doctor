package com.github.netexample.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
	protected static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
	
	public static final String WEB_ROOT = 
			System.getProperty("user.dir") + File.separator + "webroot";
	
	private static final String SHUTDOWN_COMMAND = "/shutdown";
	
	private boolean isShutdown = false;

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}
	
	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		} catch (IOException e) {
			logger.info("{serverSocket:{}. 异常退出}",e.getMessage());
			System.exit(1);
		}
		
		while (!isShutdown) {
			Socket socket = null;
			OutputStream out;
			InputStream in;
			try {
				socket = serverSocket.accept();
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
				Request request = new Request(in);
				request.parse();
				
				Response response = new Response(out);
				response.setRequest(request);
				response.sendStaticResource();
				
				socket.close();
				
				isShutdown = request.getUri().equals(SHUTDOWN_COMMAND);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

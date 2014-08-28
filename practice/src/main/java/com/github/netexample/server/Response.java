package com.github.netexample.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response {
	protected static final Logger logger = LoggerFactory.getLogger(Response.class);
	private static final int BUFFER_SIZE = 1024;
	
	private OutputStream outputStream;
	private Request request;
	
	public Response(OutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void sendStaticResource() throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream fileInputStream = null;
		try {
			File file = new File(HttpServer.WEB_ROOT,request.getUri());
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);
				int readNum = fileInputStream.read(buffer);
				while (-1 != readNum) {
					outputStream.write(buffer, 0, readNum);
					readNum = fileInputStream.read(buffer);
				}
			}else {
				//file not found
				String errorMessage  = "HTTP/1.1 404 File Not Found \r\n" +
						"Content-Type:test/html\r\r" + 
						"Content-Length:23\r\n" +
						"\r\n" +
						"<h1> File Not Found </h1>";
				outputStream.write(errorMessage.getBytes());
			}
		} catch (IOException e) {
			
			logger.info("{sendStaticResource:{}}",e.getMessage());
			
		}finally{
			
			if (null!= fileInputStream) {
				fileInputStream.close();
			}
		}
	}
}

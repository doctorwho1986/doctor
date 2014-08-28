package com.github.netexample.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Response implements ServletResponse{
	protected static final Logger logger = LoggerFactory.getLogger(Response.class);
	private static final int BUFFER_SIZE = 1024;
	
	private OutputStream outputStream;
	private Request request;
	
	private PrintWriter writer;
	
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
			File file = new File(Constants.WEB_ROOT,request.getUri());
			if (file.exists()) {
				fileInputStream = new FileInputStream(file);
				int readNum = fileInputStream.read(buffer);
				while (-1 != readNum) {
					outputStream.write(buffer, 0, readNum);
					readNum = fileInputStream.read(buffer);
				}
			}else {
				//file not found
				logger.info("{sendStaticResource:{} {} file not found}",Constants.WEB_ROOT,request.getUri());
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

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(outputStream,true);
		return writer;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLength(int len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentLengthLong(long len) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContentType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBufferSize(int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLocale(Locale loc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
}

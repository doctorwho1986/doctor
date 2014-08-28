package com.github.netexample.server;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Request {
	protected static final Logger logger = LoggerFactory.getLogger(Request.class);
	private String uri;
	private InputStream inputStream;
	
	public Request(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public void parse() {
		StringBuffer request = new StringBuffer(2048);
		byte[] buffer = new byte[2048];
		
		int i;
		try {
			i = inputStream.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		
		for (byte b : buffer) {
			request.append((char)b);
		}
		
		uri = parseUri(request.toString());
		logger.info(uri);
	}
	
	
	/**
	 * 
	 * @param requesString  GET /index.html HTTP/1.1
	 * @return String  /index.html
	 */
	private String parseUri(String requesString){
		int blankfirst,blanksecond;
		blankfirst = requesString.indexOf(' ');
		if (-1 != blankfirst) {
			blanksecond = requesString.indexOf(' ', blankfirst + 1);
			if (blanksecond > blankfirst) {
				return requesString.substring(blankfirst + 1, blanksecond);
			}
		}
		return null;
	}
	
	public String getUri() {
		return uri;
	}
}

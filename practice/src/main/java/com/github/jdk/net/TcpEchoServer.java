/*
 * Copyright (C) 2014 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jdk.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author doctor
 *
 * @date 2014年9月8日 下午11:45:50
 */
public class TcpEchoServer {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private int port;
	public TcpEchoServer(int port){
		this.port = port;
	}
	
	public void start() throws IOException{
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			while(true){
				
				Socket clientSocket = serverSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())),true);
				
				while(true){
					String readLine = reader.readLine();
					if (null == readLine) {
						break;
					}
					printWriter.println("server :" + readLine);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			serverSocket.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		new TcpEchoServer(8189).start();

	}

}

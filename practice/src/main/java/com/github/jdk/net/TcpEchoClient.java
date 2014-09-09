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
import java.net.Socket;
import java.util.Scanner;

/**
 * @author doctor
 *
 * @date 2014年9月8日 下午11:56:44
 */
public class TcpEchoClient {
	private String serverIp;
	private int serverPort;
	
	public TcpEchoClient(String serverIp,int serverPort){
		this.serverIp = serverIp;
		this.serverPort = serverPort;
	}
	
	public void start() throws IOException{
		Socket socket = null;
		try {
			socket = new Socket(serverIp, serverPort);
			PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader consoleRead = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				String nextLine = consoleRead.readLine();
				printWriter.println(nextLine);
				
				String readLine = reader.readLine();
				System.out.println(readLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			socket.close();
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		new TcpEchoClient("127.0.0.1", 8089).start();

	}

}

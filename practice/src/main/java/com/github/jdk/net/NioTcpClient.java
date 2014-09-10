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

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author doctor
 *
 * @date 2014年9月10日 下午9:42:47
 */
public class NioTcpClient {
	private String hostName;
	private int port;
	
	public NioTcpClient(String hostName,int port) {
		this.hostName = hostName;
		this.port = port;
	}

	
	public void start() throws IOException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		
		if (!socketChannel.connect(new InetSocketAddress(hostName,port))) {
			while (!socketChannel.finishConnect()) {
				System.out.println(".");
			}
		}
		
		ByteBuffer writeBuffer = ByteBuffer.wrap("sdjfkdjkfjdlf".getBytes());
		while (writeBuffer.hasRemaining()) {
			socketChannel.write(writeBuffer);
		}
		ByteBuffer readbBuffer = ByteBuffer.allocateDirect(1024);
		socketChannel.read(readbBuffer);
		CharBuffer charBuffer = Charset.forName("utf-8").decode(readbBuffer);
		System.out.println(charBuffer.toString());
		
		socketChannel.close();
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String hostName = "127.0.0.1";
		int port = 8980;
		new NioTcpClient(hostName, port).start();
	}

}

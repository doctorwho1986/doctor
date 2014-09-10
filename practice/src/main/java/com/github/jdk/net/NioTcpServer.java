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
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author doctor
 *
 * @date 2014年9月10日 上午12:05:11
 */
public class NioTcpServer {
	private static final int BUFSIZE = 256;
	private static final int TIMEOUT = 3000;
	private int port;
	private String hostName;
	
	public NioTcpServer(String hostName, int port) {
		this.hostName = hostName;
		this.port = port;
	}
	
	public void start() throws IOException{
		Selector selector = Selector.open();
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.socket().bind(new InetSocketAddress(hostName, port));
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		while (true) {
			if (0 == selector.select(TIMEOUT)) {
//				System.out.println(".");
				continue;
			}
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				
				SelectionKey selectionKey = iterator.next();
				
				if (selectionKey.isAcceptable()) {
					SocketChannel socketChannel = ((ServerSocketChannel)selectionKey.channel()).accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(BUFSIZE));
					
				}
				
				if (selectionKey.isReadable()) {
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					ByteBuffer buf = (ByteBuffer) selectionKey.attachment();
					int read = channel.read(buf);
					if (read == -1) {
						channel.close();
					}else if (read > 0) {
						selectionKey.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
					}
					
				}
				
				if (selectionKey.isValid() && selectionKey.isWritable()) {
					ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
					buffer.flip();
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					channel.write(buffer);
					
					if (!buffer.hasRemaining()) {
						selectionKey.interestOps(SelectionKey.OP_READ);
					}
					
					buffer.compact();
				}
				
				iterator.remove();
			}
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String hostName = "127.0.0.1";
		int port = 8980;
		new NioTcpServer(hostName, port).start();

	}

}

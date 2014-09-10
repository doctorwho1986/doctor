package com.github.jdk.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class BlockingTcpEchoServer {
	private int port ;
	private String ip;
	public BlockingTcpEchoServer(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() {
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()){
			
			if (serverSocketChannel.isOpen()) {
				serverSocketChannel.configureBlocking(true);
				serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4*1024);
				serverSocketChannel.bind(new InetSocketAddress(ip, port));
				
				while(true){
					try (SocketChannel clientSocketChannel = serverSocketChannel.accept()){
						while (true) {
//							while (-1 != clientSocketChannel.read(byteBuffer)) {
//								byteBuffer.flip();
//								clientSocketChannel.write(byteBuffer);
//								
//								if (byteBuffer.hasRemaining()) {
//									byteBuffer.compact();
//								}else {
//									byteBuffer.clear();
//								}
//							}
							
							clientSocketChannel.read(byteBuffer);
							byteBuffer.flip();
							clientSocketChannel.write(byteBuffer);
							byteBuffer.clear();
							
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port =8888;
		new BlockingTcpEchoServer(ip, port).start();

	}

}

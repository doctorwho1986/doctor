package com.github.jdk.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class BlockingTcpEchoClient {
	private String serverIp;
	private int port;
	public BlockingTcpEchoClient(String serverIp,int port) {
		this.serverIp = serverIp;
		this.port = port;
	}
	
	public void start() {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
		Charset charset = Charset.forName("utf-8");
		CharsetDecoder decoder = charset.newDecoder();
		try (SocketChannel socketChannel = SocketChannel.open()){
			
			socketChannel.configureBlocking(true);
			socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
			socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128*1024);
			socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128*1024);
			socketChannel.setOption(StandardSocketOptions.SO_LINGER, 5);
			
			socketChannel.connect(new InetSocketAddress(serverIp, port));
			if (socketChannel.isConnected()) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in,charset));
				while (true) {
					String readLine = reader.readLine();
					ByteBuffer wrap = ByteBuffer.wrap(readLine.getBytes());
					socketChannel.write(wrap);
					//注释掉，阻塞模式read，一直阻塞，不会再继续了。。。
//					while (-1 != socketChannel.read(byteBuffer)) {
//						byteBuffer.flip();
//						CharBuffer charBuffer = decoder.decode(byteBuffer);
//						System.out.println(charBuffer.toString());
//						if (byteBuffer.hasRemaining()) {
//							byteBuffer.compact();
//						}else {
//							byteBuffer.clear();
//						}
//					}
					socketChannel.read(byteBuffer);
					byteBuffer.flip();
					CharBuffer charBuffer = decoder.decode(byteBuffer);
					System.out.println();
					System.out.println(charBuffer.toString());
					System.out.println();
					byteBuffer.clear();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		new BlockingTcpEchoClient("127.0.0.1", 8888).start();

	}

}

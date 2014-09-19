package com.github.zeromq.zguide;

import java.util.concurrent.TimeUnit;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
/**
 * 
 * @author see http://zguide.zeromq.org/page:all#ZeroMQ-s-Built-In-Proxy-Function
 *
 */
public class Chapter2BuiltInProxyFunction {

	public static void main(String[] args) {
		new Thread(()-> server()).start();
		new Thread(()-> server()).start();
		new Thread(()-> server()).start();
		new Thread(()-> server()).start();
		new Thread(()-> client()).start();
		new Thread(()-> client()).start();
		new Thread(()-> client()).start();
		new Thread(()-> client()).start();
		
		msgqueue();

	}
	
	
	public static void msgqueue() {
		Context context = ZMQ.context(1);
		Socket clients = context.socket(ZMQ.ROUTER);
		clients.bind("tcp://*:6666");
		Socket servers = context.socket(ZMQ.DEALER);
		servers.bind("tcp://*:6667");
		
		ZMQ.proxy(clients, servers, null);
		
		clients.close();
		servers.close();
		context.term();
	}

	
	public static void server() {
		Context context = ZMQ.context(1);
		Socket server = context.socket(ZMQ.REP);
		server.connect("tcp://localhost:6667");
		
		try {
			while (!Thread.currentThread().isInterrupted()) {
				String recvStr = server.recvStr(ZMQ.PAIR);
				System.out.println("server " + Thread.currentThread().getName() + " receive: " + recvStr);
				server.send(recvStr + " server " + Thread.currentThread().getName(), ZMQ.PAIR);
				
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			server.close();
			context.term();
		}
	}
	
	public static void client() {
		Context context = ZMQ.context(1);
		Socket client = context.socket(ZMQ.REQ);
		client.connect("tcp://localhost:6666");
		
		try {
			while (!Thread.currentThread().isInterrupted()) {
				client.send("hello" + "  client " + Thread.currentThread().getName(), ZMQ.PAIR);
				TimeUnit.SECONDS.sleep(1);
				String recvStr = client.recvStr(ZMQ.PAIR);
				System.out.println("client " + Thread.currentThread().getName() + " receive:" + recvStr);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			client.close();
			context.term();
		}
	}
}

package com.github.springcorepractice;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

import javax.net.ServerSocketFactory;

import org.springframework.util.SocketUtils;

public class SocketUtilsPractice {

	public static void main(String[] args) {
		int availableTcpPort = SocketUtils.findAvailableTcpPort(1000);
		System.out.println(availableTcpPort);

		System.out.println(isAvaliableTcpPort(33));
		System.out.println(isAvaliableTcpPort(65523));

		System.out.println(isAvaliableUdpPort(45));
		System.out.println(isAvaliableUdpPort(4567));
		
		System.out.println(SocketType.TCP.isPortAvaliable(898));
		System.out.println(SocketType.TCP.isPortAvaliable(80));
		System.out.println(SocketType.UDP.isPortAvaliable(80));
	}

	private static boolean isAvaliableTcpPort(int port) {
		try (
				ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port)) {

			return true;
		} catch (IOException e) {
			return false;
		}
	}

	private static boolean isAvaliableUdpPort(int port){
		try(DatagramSocket datagramSocket = new DatagramSocket(port)) {
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private static enum SocketType{
		TCP {
			@Override
			public boolean isPortAvaliable(int port) {
				try(ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket(port)) {
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		},
		
		UDP {
			@Override
			public boolean isPortAvaliable(int port) {
				try(DatagramSocket datagramSocket = new DatagramSocket(port)) {
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		};
		
		
		public abstract boolean isPortAvaliable(int port);
	}
}

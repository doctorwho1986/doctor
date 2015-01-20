package com.doctor.elasticsearch_1_4_2.classes;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.elasticsearch.common.MacAddressProvider;

public class MacAddressProviderPractice {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		byte[] address = MacAddressProvider.getSecureMungedAddress();
		System.out.println(macToHex(address));

		
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface networkInterface = (NetworkInterface) networkInterfaces.nextElement();
			if (networkInterface.isLoopback()|| networkInterface.isVirtual()) {
				System.out.println(networkInterface);
				 
			}
		}
	}

	private static String macToHex(byte[] address) {
		StringBuilder stringBuilder = new StringBuilder(66);
		for (byte b : address) {
			int temp = b & 0xff;
			String hexString = Integer.toHexString(temp);
			stringBuilder.append(hexString).append("-");
		}
		stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("-"));
		return stringBuilder.toString();
	}

}

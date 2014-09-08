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

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author doctor
 *
 * @date 2014年9月8日 下午9:25:53
 */
public class InetAddressPractice {

	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
		if (null == networkInterfaces) {
			System.err.println("no network interfaces");
			return;
		}
		
		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface networkInterface = networkInterfaces.nextElement();
			System.out.println("network interface :" + networkInterface.getName());
			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			if (!inetAddresses.hasMoreElements()) {
				System.out.println("no inet address");
				break;
			}
			
			while (inetAddresses.hasMoreElements()) {
				InetAddress inetAddress = inetAddresses.nextElement();
				String netAddressType = "IPv4  ";
				if (inetAddress instanceof Inet6Address) {
					netAddressType = "IPv6 ";
				}
				System.out.println("net address:" + netAddressType + inetAddress.getHostAddress());
				
			}
		}
	}

}

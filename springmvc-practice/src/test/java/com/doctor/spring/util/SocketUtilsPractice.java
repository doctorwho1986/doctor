package com.doctor.spring.util;

import java.util.SortedSet;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.util.SocketUtils;

/**
 * @see org.springframework.util.SocketUtils
 * @author doctor
 *
 * @since 2014年12月30日 下午9:14:50
 */
public class SocketUtilsPractice {
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test_findAvailableTcpPort(){
		int availableTcpPort = SocketUtils.findAvailableTcpPort();
		System.out.println(availableTcpPort);
	}
	
	@Test
	public void test_findAvailableTcpPort2(){
		int availableTcpPort = SocketUtils.findAvailableTcpPort(SocketUtils.PORT_RANGE_MIN);
		System.out.println(availableTcpPort);
	}
	
	@Test
	public void test_findAvailableTcpPort3(){
		int availableTcpPort = SocketUtils.findAvailableTcpPort(SocketUtils.PORT_RANGE_MIN, SocketUtils.PORT_RANGE_MIN+10);
		System.out.println(availableTcpPort);
	}

	
	@Test
	public void test_findAvailableTcpPorts(){
		SortedSet<Integer> availableTcpPorts = SocketUtils.findAvailableTcpPorts(6, SocketUtils.PORT_RANGE_MIN, SocketUtils.PORT_RANGE_MIN+10);
		availableTcpPorts.forEach(System.out::println);
		
		try {
			SocketUtils.findAvailableTcpPorts(16, SocketUtils.PORT_RANGE_MIN, SocketUtils.PORT_RANGE_MIN+10);
		} catch (Exception e) {
		}
	}
}

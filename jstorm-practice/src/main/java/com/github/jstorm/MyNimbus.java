package com.github.jstorm;

import com.alibaba.jstorm.daemon.nimbus.NimbusServer;

public class MyNimbus {

	public static void main(String[] args) {
		try {
			NimbusServer.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package com.github.jdkcommon;

import java.lang.management.ManagementFactory;

public class GetPid {
	private static int pid = -1;

	public static int getPid() {
		if (pid > 0) {
			return pid;
		}
		
		String vm_name = ManagementFactory.getRuntimeMXBean().getName(); //"pid@hostname" 
		try {
			return Integer.parseInt(vm_name.substring(0, vm_name.indexOf("@")));
			
		} catch (Throwable e) {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getPid());
	}
}

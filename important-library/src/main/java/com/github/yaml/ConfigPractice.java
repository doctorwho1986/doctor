package com.github.yaml;

import java.util.List;

public class ConfigPractice {
	
	private String javaLibraryPath;
	private String stormLocalDir;
	private List<String> stormZookeeperServers;
	public String getJavaLibraryPath() {
		return javaLibraryPath;
	}
	public void setJavaLibraryPath(String javaLibraryPath) {
		this.javaLibraryPath = javaLibraryPath;
	}
	public String getStormLocalDir() {
		return stormLocalDir;
	}
	public void setStormLocalDir(String stormLocalDir) {
		this.stormLocalDir = stormLocalDir;
	}
	public List<String> getStormZookeeperServers() {
		return stormZookeeperServers;
	}
	public void setStormZookeeperServers(List<String> stormZookeeperServers) {
		this.stormZookeeperServers = stormZookeeperServers;
	}
	
	
	
}

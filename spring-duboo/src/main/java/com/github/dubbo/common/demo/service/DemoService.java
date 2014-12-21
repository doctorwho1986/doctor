package com.github.dubbo.common.demo.service;

import java.util.List;

import com.github.dubbo.common.demo.entity.User;

/**
 * @author doctor
 *
 * @since 2014年12月21日 上午10:30:04
 */
public interface DemoService {
	String sayHello(String name);
	List<User> getUsers();
}

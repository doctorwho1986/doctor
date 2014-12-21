package com.github.dubbo.provider.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.dubbo.common.demo.entity.User;
import com.github.dubbo.common.demo.service.DemoService;

/**
 * @author doctor
 *
 * @since 2014年12月21日 上午10:45:33
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

	@Override
	public List<User> getUsers() {
		List<User> list = new ArrayList<>();
		list.add(new User("doctor", 2488, "man"));
		list.add(new User("rose", 25, "woman"));
		list.add(new User("clara oswin oswald", 25, "woman"));
		return list;
	}

}

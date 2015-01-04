package com.github.dubbo.common.demo.service;

/**
 * 多增加个接口服务，查看spring context里面bean的定义。
 * 每个<dubbo:service ../> 是一个注册在spring容器的 ServiceBean （实现类ApplicationListener接口），
 * 这样，在容器初始化后，每个dubbo服务bean都会以事件机制暴露服务。ServiceBean 的事件方法里面就是服务暴露（注册）
 * 
 * @author doctor
 *
 * @time   2015年1月4日 下午4:46:09
 */
public interface DubboDemoService {

}

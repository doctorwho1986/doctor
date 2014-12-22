package com.github.dubbo.common.demo.service;

import java.util.List;

/**
 * dubbo　merger 接口声明
 * 对于要merger　接口中方法返回类型的注意事项， * 
 * 详细查看com.alibaba.dubbo.rpc.cluster.merger
 * 这个包下面的类，也就是dubbo支持的返回类型
 * @author doctor
 *
 * @since 2014年12月22日 下午9:54:56
 */
public interface MergeService {
	List<String> get(int count);
}

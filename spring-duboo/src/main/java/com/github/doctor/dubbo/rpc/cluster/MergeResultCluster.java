package com.github.doctor.dubbo.rpc.cluster;

import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.alibaba.dubbo.rpc.cluster.Directory;

public class MergeResultCluster implements Cluster {

	@Override
	public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
		return new MergeResultClusterInvoker<T>(directory);
	}

}

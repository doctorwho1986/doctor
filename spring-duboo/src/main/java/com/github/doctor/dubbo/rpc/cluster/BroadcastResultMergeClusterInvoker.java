package com.github.doctor.dubbo.rpc.cluster;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.cluster.Merger;
import com.alibaba.dubbo.rpc.cluster.merger.MergerFactory;
import com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker;

/**
 * BroadcastCluster + MergeCluster 调用集群中每个接口，汇总结果，（用于jstorm bolt启动dubbo，但对于bolt实例数目 一个jvm 限制为1个,）
 * 
 * @author doctor
 *
 * @time   2014年12月25日 下午4:47:03
 */

public class BroadcastResultMergeClusterInvoker<T> extends AbstractClusterInvoker<T> {
	private static final Logger log = LoggerFactory.getLogger(BroadcastResultMergeClusterInvoker.class);

	private ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("mergeable-cluster-executor", true));

	public BroadcastResultMergeClusterInvoker(Directory<T> directory) {
		super(directory);
	}

	@Override
	protected Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
		String merger = getUrl().getMethodParameter(invocation.getMethodName(), Constants.MERGER_KEY);

		Class<?> returnType;
		try {
			returnType = getInterface().getMethod(
					invocation.getMethodName(), invocation.getParameterTypes()).getReturnType();
		} catch (NoSuchMethodException e) {
			returnType = null;
		}

		if (ConfigUtils.isEmpty(merger)) { // 如果方法无Merge
			throw new RpcException(new StringBuilder(32)
					.append("Can not merge result because missing method [ ")
					.append(merger)
					.append(" ] in class [ ")
					.append(returnType.getClass().getName())
					.append(" ]")
					.toString());
		}

		RpcException exception = null;
		List<Result> resultList = new ArrayList<Result>();

		for (final Invoker<T> invoker : invokers) {
			try {
				Result result = invoker.invoke(invocation);
				resultList.add(result);
			} catch (RpcException e) {
				exception = e;
				log.warn(e.getMessage(), e);
			} catch (Throwable e) {
				exception = new RpcException(e.getMessage(), e);
				log.warn(e.getMessage(), e);
			}
		}
		if (exception != null) {
			throw exception;
		}

		if (resultList.size() == 0) {
			return new RpcResult((Object) null);
		} else if (resultList.size() == 1) {
			return resultList.iterator().next();
		}

		if (returnType == void.class) {
			return new RpcResult((Object) null);
		}

		Object result = null;
		if (merger.startsWith(".")) {
			merger = merger.substring(1);
			Method method;
			try {
				method = returnType.getMethod(merger, returnType);
			} catch (NoSuchMethodException e) {
				throw new RpcException(new StringBuilder(32)
						.append("Can not merge result because missing method [ ")
						.append(merger)
						.append(" ] in class [ ")
						.append(returnType.getClass().getName())
						.append(" ]")
						.toString());
			}
			if (method != null) {
				if (!Modifier.isPublic(method.getModifiers())) {
					method.setAccessible(true);
				}
				result = resultList.remove(0).getValue();
				try {
					if (method.getReturnType() != void.class
							&& method.getReturnType().isAssignableFrom(result.getClass())) {
						for (Result r : resultList) {
							result = method.invoke(result, r.getValue());
						}
					} else {
						for (Result r : resultList) {
							method.invoke(result, r.getValue());
						}
					}
				} catch (Exception e) {
					throw new RpcException(
							new StringBuilder(32)
									.append("Can not merge result: ")
									.append(e.getMessage()).toString(),
							e);
				}
			} else {
				throw new RpcException(
						new StringBuilder(32)
								.append("Can not merge result because missing method [ ")
								.append(merger)
								.append(" ] in class [ ")
								.append(returnType.getClass().getName())
								.append(" ]")
								.toString());
			}
		} else {
			Merger resultMerger;
			if (ConfigUtils.isDefault(merger)) {
				resultMerger = MergerFactory.getMerger(returnType);
			} else {
				resultMerger = ExtensionLoader.getExtensionLoader(Merger.class).getExtension(merger);
			}
			if (resultMerger != null) {
				List<Object> rets = new ArrayList<Object>(resultList.size());
				for (Result r : resultList) {
					rets.add(r.getValue());
				}
				result = resultMerger.merge(
						rets.toArray((Object[]) Array.newInstance(returnType, 0)));
			} else {
				throw new RpcException("There is no merger to merge result.");
			}
		}
		return new RpcResult(result);
	}

	public Class<T> getInterface() {
		return directory.getInterface();
	}

	public URL getUrl() {
		return directory.getUrl();
	}

	public boolean isAvailable() {
		return directory.isAvailable();
	}

	public void destroy() {
		directory.destroy();
	}

}

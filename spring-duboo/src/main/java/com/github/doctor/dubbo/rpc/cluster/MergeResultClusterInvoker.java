package com.github.doctor.dubbo.rpc.cluster;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections4.map.MultiValueMap;
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
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.cluster.Merger;
import com.alibaba.dubbo.rpc.cluster.merger.MergerFactory;
import com.alibaba.dubbo.rpc.cluster.support.AbstractClusterInvoker;

/**
 * 可以以MergeableClusterInvoker 改写86行，返回结果用可以存储key重复的map结果即可。也可以把那部分处理替代相应
 * 部分。因为父类不一样。
 * 
 * @author doctor
 *
 * @time 2014年12月25日 下午4:47:03
 */

public class MergeResultClusterInvoker<T> extends AbstractClusterInvoker<T> {
	private static final Logger log = LoggerFactory.getLogger(MergeResultClusterInvoker.class);

	private ExecutorService executor = Executors.newCachedThreadPool(new NamedThreadFactory("mergeable-cluster-executor", true));

	public MergeResultClusterInvoker(Directory<T> directory) {
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

		// Map<String, Future<Result>> results = new HashMap<String, Future<Result>>();
		MultiValueMap<String, Future<Result>> results = new MultiValueMap<String, Future<Result>>();
		for (final Invoker<T> invoker : invokers) {
			Future<Result> future = executor.submit(new Callable<Result>() {
				public Result call() throws Exception {
					return invoker.invoke(new RpcInvocation(invocation, invoker));
				}
			});
			results.put(invoker.getUrl().getServiceKey(), future);
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

		// for (final Invoker<T> invoker : invokers) {
		// try {
		// Result result = invoker.invoke(invocation);
		// resultList.add(result);
		// } catch (RpcException e) {
		// exception = e;
		// log.warn(e.getMessage(), e);
		// } catch (Throwable e) {
		// exception = new RpcException(e.getMessage(), e);
		// log.warn(e.getMessage(), e);
		// }
		// }
		int timeout = getUrl().getMethodParameter(invocation.getMethodName(), Constants.TIMEOUT_KEY, Constants.DEFAULT_TIMEOUT);
		for (Entry<String, Object> entry : results.entrySet()) {
			ArrayList<Future<Result>> futures = (ArrayList<Future<Result>>) entry.getValue();
			try {
				for (Future<Result> future : futures) {
					Result r = future.get(timeout, TimeUnit.MILLISECONDS);
					if (r.hasException()) {
						log.error(new StringBuilder(32).append("Invoke ")
								.append(getGroupDescFromServiceKey(entry.getKey()))
								.append(" failed: ")
								.append(r.getException().getMessage()).toString(),
								r.getException());
					} else {
						resultList.add(r);
					}
				}
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				throw new RpcException(new StringBuilder(32)
						.append("Failed to invoke service ")
						.append(entry.getKey())
						.append(": ")
						.append(e.getMessage()).toString(),
						e);
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

	private String getGroupDescFromServiceKey(String key) {
		int index = key.indexOf("/");
		if (index > 0) {
			return new StringBuilder(32).append("group [ ")
					.append(key.substring(0, index)).append(" ]").toString();
		}
		return key;
	}
}

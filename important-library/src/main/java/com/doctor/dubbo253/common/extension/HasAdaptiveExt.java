package com.doctor.dubbo253.common.extension;

import com.alibaba.cooma.Extension;
import com.alibaba.dubbo.common.URL;

@Extension("impl1")
public interface HasAdaptiveExt {
	String echo(URL url, String s);
}

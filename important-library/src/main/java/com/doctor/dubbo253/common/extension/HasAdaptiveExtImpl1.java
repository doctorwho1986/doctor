package com.doctor.dubbo253.common.extension;

import com.alibaba.dubbo.common.URL;

public class HasAdaptiveExtImpl1 implements HasAdaptiveExt {

	@Override
	public String echo(URL url, String s) {
		return this.getClass().getSimpleName();
	}

}

package com.doctor.dubbo253.common.extension;

import com.alibaba.cooma.ExtensionLoader;
import com.alibaba.dubbo.common.URL;

public class HasAdaptiveExt_ManualAdaptive implements HasAdaptiveExt {

	@Override
	public String echo(URL url, String s) {
		HasAdaptiveExt addExt1 = ExtensionLoader.getExtensionLoader(HasAdaptiveExt.class).getExtension(url.getParameter("key"));
		return addExt1.echo(url, s);
	}

}

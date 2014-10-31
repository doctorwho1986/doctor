package com.doctor.dubbo253.common.extension;


import org.junit.Test;

import com.alibaba.cooma.ExtensionLoader;

public class DubboExtension {

	@Test
	public void test_useAdaptiveClass() throws Exception {
		ExtensionLoader<HasAdaptiveExt> loader = ExtensionLoader.getExtensionLoader(HasAdaptiveExt.class);
		HasAdaptiveExt ext = loader.getDefaultExtension();
	}

	
}

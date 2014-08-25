/*
 * Copyright (C) 2009 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.guava;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Splitter;

/**
 * @author doctor
 *
 * @date 2014年8月25日 下午8:53:56
 */
public class SplitterPractice {

	@Test
	public void test() {
		List<String> splitToList = Splitter.on(",").splitToList(",,name,doctor,,");
		List<String> expectlList = new ArrayList<String>();
		expectlList.add("");
		expectlList.add("");
		expectlList.add("name");
		expectlList.add("doctor");
		expectlList.add("");
		expectlList.add("");
		
		Assert.assertEquals(expectlList, splitToList);
		
		//jdk自带的会丢去后面的空内容，有时候，并不是我们需要的
		String[] split = ",,name,doctor,,".split(",");
		expectlList.remove(splitToList.size()-1);
		expectlList.remove(splitToList.size()-2);
		Assert.assertEquals(expectlList, Arrays.asList(split));

	}

}

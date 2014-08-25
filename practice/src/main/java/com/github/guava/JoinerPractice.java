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


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Joiner;

/**
 * @author doctor
 *
 * @date 2014年8月25日 下午8:04:28
 */
public class JoinerPractice {

	private String[] str = {"aa","bb","abc"};
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test() {
		String join = Joiner.on('|').skipNulls().join(str);
		Assert.assertEquals(StringUtils.join(str, '|'), join);
	}
	
	@Test
	public void test_null(){
		thrown.expect(NullPointerException.class);
		Joiner.on(",").join("dj",null);
	}
	
	@Test
	public void test_map(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "doctor");
		map.put("address", "深圳");
		String join = Joiner.on("#").withKeyValueSeparator("=").join(map);
		
		Assert.assertEquals(mapJoinWith(map,"=","#"), join);
	}
	
	
	private String mapJoinWith(Map<?,?> map, String keyValueSeparator,String separator ){
		StringBuffer stringBuffer = new StringBuffer();
		for (Object key : map.keySet()) {
			stringBuffer.append(key).append(keyValueSeparator).append(map.get(key)).append(separator);
		}
		stringBuffer.setLength(stringBuffer.length() - separator.length());
		return stringBuffer.toString();
	}

}

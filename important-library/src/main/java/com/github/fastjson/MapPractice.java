/*
 * Copyright (C) 2014 The doctor Authors
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
package com.github.fastjson;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author doctor
 *
 * @date 2014年9月18日 下午9:32:30
 */
public class MapPractice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("name", "doctor");
		map.put("age", "1118");
		map.put("sex", "man");
		String jsonString = JSON.toJSONString(map);
		System.out.println(jsonString);
		
		InputStream resourceAsStream = MapPractice.class.getResourceAsStream("/fastjson/map1.json");
		String jString = null;
		try {
			 jString = IOUtils.toString(resourceAsStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		Map<?, ?> parse = JSON.parseObject(jString,Map.class);
		System.out.println(parse);
		for (Object key : parse.keySet()) {
			System.out.println(key + ":" + parse.get(key));
		}
		
		
		
		InputStream resourceAsStream2 = MapPractice.class.getResourceAsStream("/fastjson/map2.json");
		String jString2 = null;
		try {
			jString2 = IOUtils.toString(resourceAsStream2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<?,?> parseObject = JSON.parseObject(jString2, Map.class);
		System.out.println(JSON.toJSON(parseObject));
	}

	
}

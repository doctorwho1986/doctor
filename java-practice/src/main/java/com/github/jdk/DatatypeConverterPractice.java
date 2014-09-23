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
package com.github.jdk;

import java.util.Base64;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author doctor
 *
 * @date 2014年9月23日 下午9:00:18
 */
public class DatatypeConverterPractice {
	private static final Logger log = LoggerFactory.getLogger(DatatypeConverterPractice.class);
	
	@Test
	public void parseBase64Binary(){
		String lexicalXSDBase64Binary = "wodoctor";
		byte[] base64Binary = DatatypeConverter.parseBase64Binary(lexicalXSDBase64Binary );
		System.out.println(base64Binary.length);
		System.out.println(DatatypeConverter.printBase64Binary(base64Binary));
		Assert.assertEquals(lexicalXSDBase64Binary, DatatypeConverter.printBase64Binary(base64Binary));
	
		
		String encodeToString = Base64.getEncoder().encodeToString(lexicalXSDBase64Binary.getBytes());
		System.out.println(encodeToString);
		byte[] decode = Base64.getDecoder().decode(encodeToString);
		Assert.assertEquals(lexicalXSDBase64Binary, new String(decode));
		
		
		
		UUID randomUUID = UUID.randomUUID();
		log.info("{uuid: '{}'}",randomUUID);
		String uuidEncode = Base64.getEncoder().encodeToString(randomUUID.toString().getBytes());
		log.info("{encode result: '{}'}",uuidEncode);
		byte[] uuidDecode = Base64.getDecoder().decode(uuidEncode);
		Assert.assertEquals(randomUUID.toString(), new String(uuidDecode));
		
		
		randomUUID = UUID.randomUUID();
		log.info("{uuid: '{}'}",randomUUID);
		String base64Binary2 = DatatypeConverter.printBase64Binary(randomUUID.toString().getBytes());
		log.info("{encode result: '{}'}",base64Binary2);
		byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(base64Binary2);
		Assert.assertEquals(randomUUID.toString(), new String(parseBase64Binary));
	}
}

package com.github.yaml;

import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSON;


public class YamlPractice {
	private static final Logger LOG = LoggerFactory.getLogger(YamlPractice.class);

	public static void main(String[] args) {
		Yaml yaml = new Yaml();
		InputStream inputStream = YamlPractice.class.getResourceAsStream("/importantLibraryYamlDefaultProp/jstorm.yaml");
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) yaml.load(inputStream);
		System.out.println(map);
		
		inputStream = YamlPractice.class.getResourceAsStream("/importantLibraryYamlDefaultProp/yamlPractice.yaml");
		
		ConfigPractice configPractice =  yaml.loadAs(inputStream,ConfigPractice.class);
		System.out.println(JSON.toJSONString(configPractice));
		String dump = yaml.dump(configPractice);
		System.out.println(dump);
		LOG.info(dump);
		LOG.info(JSON.toJSONString(configPractice));
	}

}

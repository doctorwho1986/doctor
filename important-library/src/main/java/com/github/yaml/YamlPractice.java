package com.github.yaml;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSON;


public class YamlPractice {

	public static void main(String[] args) {
		Yaml yaml = new Yaml();
		InputStream inputStream = YamlPractice.class.getResourceAsStream("/importantLibraryYamlDefaultProp/jstorm.yaml");
		Map<String, String> map = (Map<String, String>) yaml.load(inputStream);
		System.out.println(map);
		
		inputStream = YamlPractice.class.getResourceAsStream("/importantLibraryYamlDefaultProp/yamlPractice.yaml");
		
		ConfigPractice configPractice =  yaml.loadAs(inputStream,ConfigPractice.class);
		System.out.println(JSON.toJSONString(configPractice));

	}

}

package com.github.springcorepractice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.springframework.util.SerializationUtils;
import org.yaml.snakeyaml.Yaml;

public class SerializationUtilsPractice {
	private static final Yaml yaml = new Yaml();
	public static void main(String[] args) {
		PersonS personS = new PersonS("doctor", 2000, Arrays.asList("地球","火星","塔斯特母星"));
		byte[] serialize = SerializationUtils.serialize(personS);
		System.out.println(DatatypeConverter.printHexBinary(serialize));
		PersonS deserialize = (PersonS) SerializationUtils.deserialize(serialize);
		System.out.println(yaml.dump(deserialize));

	}

	public static class PersonS implements Serializable{
		private static final long serialVersionUID = 4920814188306003175L;
		private String name;
		private int age;
		private List<String> address;
		
		PersonS(String name,int age,List<String> address){
			this.name = name;
			this.age = age;
			this.address = new ArrayList<String>(address);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public List<String> getAddress() {
			return address;
		}

		public void setAddress(List<String> address) {
			this.address = address;
		}
		
		
	}
}

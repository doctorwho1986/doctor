package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;

/**
 * One reason you may choose to implement the Externalizable interface instead
 * of the Serializable interface is because Java’s default serialization is very
 * inefficient. Because the Java Serialization framework needs to ensure that
 * every object (and dependent object) is serialized, it will write even objects
 * that have default values or that might be empty and/or null. Implementing the
 * Externalizable interface also provides for finer-grained control on how your
 * class is being serialized.
 * 
 * @author doctor
 *
 * @since 2015年2月4日 下午10:02:55
 */
public class Chapter8Code {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("test.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
			Person person = new Person("doctor", "man", 28000);
			person.writeExternal(outputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));) {

			Person person2 = new Person();
			person2.readExternal(inputStream);
			System.out.println(person2);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static class Person implements Externalizable {
		private String name;
		private String sex;
		private Integer age;

		public Person() {
			// must have
		}

		public Person(String name, String sex, Integer age) {
			this.name = name;
			this.sex = sex;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeUTF(name);
			out.writeUTF(sex);
			out.writeInt(age);
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			name = in.readUTF();
			sex = in.readUTF();
			age = in.readInt();
		}

	}
}

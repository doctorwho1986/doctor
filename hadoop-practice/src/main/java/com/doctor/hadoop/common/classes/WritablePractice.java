package com.doctor.hadoop.common.classes;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.Writable;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
/**
 * hadoop序列化
 * @author doctor
 *
 * @since 2015年1月22日 上午12:55:42
 */
public class WritablePractice {

	public static void main(String[] args) throws IOException {
		Person person = new Person();
		person.setName("doctor");
		person.setBirth(LocalDate.now().minusYears(1000));
		System.out.println(person);
		DataOutputBuffer out = new DataOutputBuffer();
		person.write(out);
		
		DataInputBuffer in = new DataInputBuffer();
		in.reset(out.getData(), out.getLength());
		Person person2 = Person.read(in);
		System.out.println(person2);
		Preconditions.checkState(person.toString().equals(person2.toString()));

	}

	private static class Person implements Writable {
		private String name;
		private LocalDate birth;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDate getBirth() {
			return birth;
		}

		public void setBirth(LocalDate birth) {
			this.birth = birth;
		}

		@Override
		public void write(DataOutput out) throws IOException {
			out.writeUTF(name);
			out.writeUTF(birth.toString());

		}

		@Override
		public void readFields(DataInput in) throws IOException {
			name = in.readUTF();
			String temp = in.readUTF();
			this.birth = LocalDate.parse(temp);
		}
		
		public static Person read(DataInput in)throws IOException{
			Person person = new Person();
			person.readFields(in);
			return person;
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

		
	}

}

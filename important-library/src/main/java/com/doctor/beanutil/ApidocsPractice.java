package com.doctor.beanutil;

import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.AbstractConverter;

import com.google.common.base.MoreObjects;

/**
 * @see http://commons.apache.org/proper/commons-beanutils/javadocs/v1.9.2/apidocs/org/apache/commons/beanutils/package-summary.html#package_description
 * @author doctor
 *
 * @since 2014年11月16日 下午6:26:56
 */
public class ApidocsPractice {

	public static void main(String[] args) throws ReflectiveOperationException {
		Person person = new Person();
		person.setAge(1000);
		person.setBirth(LocalDateTime.of(1987, 1, 1, 12, 0));
		person.setName("doctor who");
		System.out.println(person);

		System.out.println();
		System.out.println(PropertyUtils.getSimpleProperty(person, "age"));
		System.out.println(PropertyUtils.getSimpleProperty(person, "birth"));
		System.out.println(PropertyUtils.getSimpleProperty(person, "name"));

		PropertyUtils.setProperty(person, "age", 2000);
		System.out.println(PropertyUtils.getSimpleProperty(person, "age"));

		Map<String, String> map = BeanUtils.describe(person);
		System.out.println();
		map.forEach((k, v) -> System.out.print(k + ":" + v + "  "));

		System.out.println();
		Person person2 = new Person();
		//java8 LocalDateTime　注册
		ConvertUtils.register(new LocalDateTimeConverter(), LocalDateTime.class);
		BeanUtils.populate(person2, map);
		System.out.println(person2);

	}

	public static class Person {
		private String name;
		private LocalDateTime birth;
		private Integer age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDateTime getBirth() {
			return birth;
		}

		public void setBirth(LocalDateTime birth) {
			this.birth = birth;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {

			return MoreObjects.toStringHelper(this).add("name", getName()).add("birth", getBirth())
					.add("age", getAge()).toString();
		}

	}

	public static class LocalDateTimeConverter extends AbstractConverter {

		public LocalDateTimeConverter() {
			super();
		}

		public LocalDateTimeConverter(Object defaultValue) {
			super(defaultValue);
		}

		@Override
		protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
			if (LocalDateTime.class.equals(type) ) {
				return type.cast(LocalDateTime.parse(value.toString()));
			}
			throw conversionException(type, value);
		}

		@Override
		protected Class<?> getDefaultType() {
			return LocalDateTime.class;
		}

	}
}

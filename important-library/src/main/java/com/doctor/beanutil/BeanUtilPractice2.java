package com.doctor.beanutil;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.SuppressPropertiesBeanIntrospector;
import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.MoreObjects;

/**
 * 自定义转换格式　　　
 * @author doctor
 *
 * @since 2014年11月19日 下午11:01:08
 */
public class BeanUtilPractice2 {
	static {
		PropertyUtils.clearDescriptors();
		PropertyUtils.addBeanIntrospector(SuppressPropertiesBeanIntrospector.SUPPRESS_CLASS);
		ConvertUtils.deregister(StringConverter.class);
		ConvertUtils.register(new MyStringConverter(), String.class);
		ConvertUtils.register(new LocalDateTimeConverter(), LocalDateTime.class);
	}

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Person person = new Person("docotor", LocalDateTime.now(), 4677);
		BeanUtils.describe(person)
				.forEach((k, v) -> System.out.println(k + ":" + v));

		Map<String, String> describe = BeanUtils.describe(person);

		Person person2 = new Person();
		BeanUtils.populate(person2, describe);
		System.out.println(person2);

	}

	public static class Person {
		private String name;
		private LocalDateTime birth;
		private Integer age;

		public Person() {
		}

		public Person(String name, LocalDateTime birth, Integer age) {
			this.name = name;
			this.birth = birth;
			this.age = age;
		}

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
			return MoreObjects.toStringHelper(this)
					.add("name", getName())
					.add("age", getAge())
					.add("birth", getBirth())
					.toString();
		}

		
	}

	/**
	 * 自定义格式转换为字符串形式　转换器
	 * 
	 * @author doctor
	 *
	 * @since 2014年11月19日 下午10:38:07
	 */
	public static final class MyStringConverter extends AbstractConverter {

		private String timePattern = "yyyy-MM-dd HH:mm:ss";
		private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);

		public MyStringConverter() {
			super();
		}

		public MyStringConverter(String timePattern) {
			super();
			if (StringUtils.isNotBlank(timePattern) && !this.timePattern.equals(timePattern)) {
				this.timePattern = timePattern;
				dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
			}

		}

		public MyStringConverter(Object defaultValue) {
			super(defaultValue);
		}

		@Override
		protected Class<?> getDefaultType() {
			return String.class;
		}

		@Override
		protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
			// We have to support Object, too, because this class is sometimes
			// used for a standard to Object conversion
			if (String.class.equals(type) || Object.class.equals(type)) {
				return type.cast(value.toString());
			}
			throw conversionException(type, value);
		}

		/**
		 * 自己加上
		 */
		@Override
		protected String convertToString(Object value) throws Throwable {
			if (value.getClass().equals(LocalDateTime.class)) {
				return LocalDateTime.class.cast(value).format(dateTimeFormatter);
			}
			return super.convertToString(value);
		}

	}

	/**
	 * LocalDateTime 从字符串到LocalDateTime的转换
	 * @author doctor
	 *
	 * @since 2014年11月19日 下午11:00:17
	 */
	public static final class LocalDateTimeConverter extends AbstractConverter {
		private String timePattern = "yyyy-MM-dd HH:mm:ss";
		private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);

		public LocalDateTimeConverter() {
			super();
		}

		public LocalDateTimeConverter(String timePattern) {
			super();
			if (StringUtils.isNotBlank(timePattern) && !this.timePattern.equals(timePattern)) {
				this.timePattern = timePattern;
				dateTimeFormatter = DateTimeFormatter.ofPattern(timePattern);
			}

		}

		public LocalDateTimeConverter(Object defaultValue) {
			super(defaultValue);
		}

		@Override
		protected Class<?> getDefaultType() {
			return LocalDateTime.class;
		}

		@Override
		protected <T> T convertToType(Class<T> type, Object value) throws Throwable {
			// We have to support Object, too, because this class is sometimes
			// used for a standard to Object conversion
			if (LocalDateTime.class.equals(type)) {
				
				return type.cast(LocalDateTime.parse(value.toString(), dateTimeFormatter));
			}
			throw conversionException(type, value);
		}
	}
}

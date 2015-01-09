package com.github.jdk;

import java.io.IOException;
import java.io.InputStream;

import com.google.common.util.concurrent.AtomicDouble;

/**
 * InstanceofVsGetclass没啥不同，新jvm都依托给父类先加载
 * @author doctor
 *
 * @time 2015年1月9日 上午10:49:54
 */
public class InstanceofVsGetclass {

	public static void main(String[] args) throws ReflectiveOperationException {
		
		ClassLoader myclasslClassLoader = new ClassLoader() {

			@Override
			protected Class<?> findClass(String name) throws ClassNotFoundException {
				String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
				InputStream inputStream = getClass().getResourceAsStream(fileName);
				if (inputStream == null) {
					throw new RuntimeException("can't find class file:" + fileName);
				}
				
				try {
					byte[] b = new byte[inputStream.available()];
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			
		};

		Object instance = myclasslClassLoader.loadClass("com.github.jdk.InstanceofVsGetclass").newInstance();
		System.out.println(instance.getClass().getClassLoader());
		System.out.println(InstanceofVsGetclass.class.getClassLoader());
		System.out.println(instance);
		System.out.println(instance.getClass());
		System.out.println(instance instanceof InstanceofVsGetclass);
		System.out.println(instance.getClass() == InstanceofVsGetclass.class);
		
		System.out.println("/////////////");
		
		instance = myclasslClassLoader.loadClass("com.google.common.util.concurrent.AtomicDouble").newInstance();
		System.out.println(instance.getClass().getClassLoader());
		System.out.println(AtomicDouble.class.getClassLoader());
		System.out.println(instance);
		System.out.println(instance.getClass());
		System.out.println(instance instanceof AtomicDouble);
		System.out.println(instance.getClass() == AtomicDouble.class);
	
	}

}

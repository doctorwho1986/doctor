package com.doctor.util;

import com.doctor.enums.IEnumDescription;

public class IEnumDescriptionUtil {
	public static <T extends IEnumDescription> T of(Class<T> type,int index){
		T[] constants = type.getEnumConstants();
		for (T t : constants) {
			if (t.getIndex() == index) {
				return t;
			}
		}
		
		throw new RuntimeException(type + " not have a valid index value :" + index);
	}
}

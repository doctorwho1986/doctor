package com.doctor.beanutil;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;

import com.google.common.base.Preconditions;
/**
 * 
 * @author doctor
 *
 * @since 2014年11月18日 上午00:12:01
 */

public class MethodUtilPractice {

	public static void main(String[] args) {
		Method matchingAccessibleMethod = MethodUtils.getMatchingAccessibleMethod(MethodUtilPractice.class, "setAge", new Class[]{Integer.class});
		Preconditions.checkArgument(matchingAccessibleMethod != null);
		
		matchingAccessibleMethod = MethodUtils.getMatchingAccessibleMethod(MethodUtilPractice.class, "setAge", new Class[]{int.class});
		Preconditions.checkArgument(matchingAccessibleMethod == null);
		
		matchingAccessibleMethod = MethodUtils.getMatchingAccessibleMethod(MethodUtilPractice.class, "setYear", new Class[]{int.class});
		Preconditions.checkArgument(matchingAccessibleMethod != null);
		
		//can match primitive parameter by passing in wrapper classes.
		matchingAccessibleMethod = MethodUtils.getMatchingAccessibleMethod(MethodUtilPractice.class, "setYear", new Class[]{Integer.class});
		Preconditions.checkArgument(matchingAccessibleMethod != null);
	}

	public String getName(){
		return "";
	}
	
	public void setName(String name) {
		
	}
	
	public Integer getAge() {
		return Integer.MAX_VALUE;
	}
	
	public void setAge(Integer age) {
		
	}
	
	public void setYear(int year) {
		
	}
}

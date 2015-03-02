package com.doctor.spring.aop.time20150302;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {

	/**
	 * You use the execution() designator to select Performance’s perform() method.
	 * The method specification starts with an asterisk, which indicates that you don’t care
	 * what type the method returns. Then you specify the fully qualified class name and the
	 * name of the method you want to select. For the method’s parameter list, you use the
	 * double dot (..), indicating that the pointcut should select any perform() method,
	 * no matter what the argument list is.
	 */
	@Pointcut("execution(* com.doctor.spring.aop.time20150302.Performance.perform(..))")
	public void performance() {
	}

	@Before("performance()")
	public void silenceCellPhones() {
		System.out.println(Audience.class + ": silence Cell Phones ");
	}

	@AfterReturning("performance()")
	public void applause() {
		System.out.println(Audience.class + ": applause ");
	}

	@AfterThrowing("performance()")
	public void demandRefund() {
		System.out.println(Audience.class + ": demandRefund");
	}

	@Around("performance()")
	public void watchPerformance(ProceedingJoinPoint pj) {
		System.out.println("watchPerformance before");
		try {
			pj.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("watchPerformance after");
		
	}
}

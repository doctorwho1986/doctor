package com.github.attention;

import static org.junit.Assert.*;

public class EnumFactory {

	public static void main(String[] args) {
		//枚举非静态方法实现工厂方法模式
		assertEquals(FordCar.class, CarFactory1.FordCar.create().getClass());
		assertEquals(BuickCar.class, CarFactory1.BuickCar.create().getClass());
		
		//枚举抽象方法实现工厂方法模式
		assertEquals(FordCar.class, CarFactory2.FordCar.create().getClass());
		assertEquals(BuickCar.class, CarFactory2.BuickCar.create().getClass());
	}
	
	interface Car{
		
	}
	
	public static class FordCar implements Car{
		
	}
	
	public static class BuickCar implements Car{
		
	}
	
	/**
	 * 枚举非静态方法实现工厂方法模式
	 * @author doctor
	 *
	 */
	enum CarFactory1{
		FordCar,BuickCar;
		public Car create(){
			switch (this) {
			case FordCar:
				return new FordCar();
			case BuickCar:
				return new BuickCar();
			default:
				throw new Error("无效参数");
			}
		}
	}
	
	/**
	 * 枚举抽象方法实现工厂方法模式
	 */
	enum CarFactory2{
		FordCar {
			@Override
			public Car create() {
				return new FordCar();
			}
		},
		
		BuickCar {
			@Override
			public Car create() {
				return new BuickCar();
			}
		};
		
		public abstract Car create();
	}
}

package com.doctor.groovy2_4_0.ebook.groovy_programming

/**
 * @author doctor
 *
 * @since 2015年2月9日 下午10:23:04
 */
class Chapter2Code {

	static main(args) {
		println  "----groovy整数就是java   Integer 类型----"
		def a = 123
		println a
		println  a.getClass()
		a = -129
		println a
		println  a.getClass()
		
		println "---groovy 浮点数就是 java BigDecimal 类型"
		def f = 12.36
		println f
		println  f.getClass()
		f = -12.56
		println  f
		println  f.getClass()
		
		println "--groovy表达式计算"
		println 1+2
		println 1.plus(2)
		println 1-1
		println 1.minus(2)
		println 1/3
		println 1%3
		
		println "groovy对象引用"
		def reference = 25
		def referenceb = reference
		println reference
		println referenceb
		referenceb = 35
		println  reference
		println  referenceb
		
		def ab = [1,2]
		def aa = ab
		println  ab
		println  aa
		aa << 34
		println ab
		println aa
		
	}

}

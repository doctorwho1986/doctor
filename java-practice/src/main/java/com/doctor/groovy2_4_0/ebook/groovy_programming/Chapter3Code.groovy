package com.doctor.groovy2_4_0.ebook.groovy_programming

/**
 * @author doctor
 *
 * @since 2015年2月9日 下午10:39:18
 */
class Chapter3Code {

	static main(args) {
		println "groovy字符串表示"
		def age = 666666
		println "age is ${age}"
		println 'age is ${age}'
		println '''age is ${age}'''
		println """age is $age"""
		println """age is \$age"""
		
		println "groovy字符串索引和索引段"
		def str = "my name is doctor"
		println str[0]
		println str[-1]
		println str[1..6 ]
		println str[1..<6]
		println str[1,3,6]
		
		println "字符串基本操作"
		println "hello" + " " + "world"
		println str-"doctor"
		println "hello" * 2
		println "hello".size()
		println "hello".count("o")
		
		println "groovy正则表达式"
		
		def regex = ~'name'
		println "name"=~regex
		println "name"=~"name"
		
		if ("name" =~ regex) {
			println "name =~ 'name'"
		}
		
		
	}

}

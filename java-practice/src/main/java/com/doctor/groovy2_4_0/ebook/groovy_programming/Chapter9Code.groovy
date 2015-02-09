package com.doctor.groovy2_4_0.ebook.groovy_programming

/**
 * @author doctor
 *
 * @since 2015年2月10日 上午12:04:42
 */
class Chapter9Code {

	static main(args) {
		println "groovy 闭包"
		def clos = {println "hello doctor"}
		clos.call()
		clos = {param -> println "hello ${param}"}
		clos.call("doctor")
		clos.call("doctor who")
		
		clos("doctor who simple closure")
	}

}

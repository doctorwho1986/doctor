package com.doctor.groovy2_4_0.ebook.programming_groovy

/**
 * @author doctor
 *
 * @since 2015年2月9日 下午9:53:43
 */
class Chapter3Code {

	static main(args) {
		String[] str = ["aa","name","doctor"]
		println "----groovy style-------"
		for (var in str) {
			println var
		}
		
		println "-----------java style"
		for (String var : str) {
			println  var
		}
	}

}

package com.doctor.groovy2_4_0.ebook.programming_groovy

/**
 * @author doctor
 *
 * @since 2015年2月9日 下午8:16:10
 */
class Chapter2Code {

	static main(args) {
		for (var in 0..2) {
			println var
		}
		println "------------"
		
		0.upto(6) {println "$it"}
		println "---------"
		2.times {println "$it"}
		println "------------"
		10.step(20,2){println "$it"}
		
		println "mvn -help".execute().text
		println "------------"
		
		println "mvn -help".execute().getClass().name
	}

}

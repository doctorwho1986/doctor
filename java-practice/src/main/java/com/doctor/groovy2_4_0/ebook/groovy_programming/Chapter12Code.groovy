package com.doctor.groovy2_4_0.ebook.groovy_programming

/**
 * @author doctor
 *
 * @since 2015年2月10日 下午7:50:03
 */
class Chapter12Code {
	def name;
	def age;

	static main(args) {
		new File(".").eachFileRecurse {println "$it"} 
		
		def code = new Chapter12Code(name:"doctor",age:66666)
		println code.name
	}

}

package com.doctor.groovy2_4_0

/**
 * @author doctor
 *
 * @since 2015年2月8日 下午6:51:14
 */
class ControlStructuresLooping {

	static main(args) {
		for (i in 0..2) {
			println i
		}
		
		//closures
		def stringList = [ "java", "perl", "python", "ruby", "c#", "cobol",
			"groovy", "jython", "smalltalk", "prolog", "m", "yacc" ];

		def stringMap = [ "Su" : "Sunday", "Mo" : "Monday", "Tu" : "Tuesday",
		   "We" : "Wednesday", "Th" : "Thursday", "Fr" : "Friday",
		   "Sa" : "Saturday" ];
	   stringList.each {println "$it"}
	   stringMap.each {key,value -> println "$key = $value"}
	   stringList.eachWithIndex {o,i -> println "$i : $o"}
	   stringMap.eachWithIndex {o,i -> println "$i : $o"}
	}

}

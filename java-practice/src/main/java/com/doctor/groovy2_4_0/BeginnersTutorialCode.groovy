package com.doctor.groovy2_4_0

/**
 * @see http://groovy.codehaus.org/Tutorial+1+-+Getting+started
 * @author doctor
 *
 * @since 2015年2月8日 下午5:01:38
 */
class BeginnersTutorialCode {

	static main(args) {
		println "hello, world"
		
		//The Groovy language has built-in support for two important data types,
		//lists and maps (Lists can be operated as arrays in Java language).
		// Lists are used to store ordered collections of data.
		// For example an integer list of your favorite integers might look like this:
		def myList = [1, 2, 3, 4, 5, 6]
		println(myList)
		println(myList[1])
		println(myList.size())
		
		def myMap =["name":"doctor","age":10000,"address":"alien"]
		println myMap.name
		println(myMap["name"])
		
		def myList2 = []
		println myList2.size()
		def myMap2 = [:]
		println  myMap2.size()
		
		//Code as data, or closures
		def square = { a -> a*a}
		println square(9)
		def resulat = [1,2,3,4,5,6,7,8,9,10].collect(square)
		println(resulat)
		
		myMap.each({k,v-> println(k+"="+v)})
		
		def fullString = ""
		def orderParts =["ab","cd","df"]
		orderParts.each({fullString += it + " "})
		println(fullString)
		
		def map = ["a":1,"b":2,"c":3]
		def sum = 0;
		map.keySet().each({sum += map[it]})
		println(sum)
		
		
		//Regular Expressions

		def b ="ab" ==~ /ab/
		println b
		println  "ab" ==~ /ac/
		
	}
}

package com.doctor.groovy2_4_0

import java.awt.TexturePaintContext.Int;

/**
 * @see http://groovy.codehaus.org/JN0025-Starting
 * @author doctor
 *
 * @since 2015年2月8日 下午6:26:34
 */
class ForThoseNewToBothJavaAndGroovy_Starting {

	static main(args) {

		//We can spawn new threads from our main thread
		int count = 100
		int mainC = 200
		
		Thread.start("thread",{
			while (count--) {
				println "thread" + count
			}
		})
		
		while (mainC--) {
			println mainC
		}
		
		def gs = ''' \tdjkfjd
                     \tdkjfkdjfk
					 \tsdfjdjf'''
		println gs
	}
	
	

}

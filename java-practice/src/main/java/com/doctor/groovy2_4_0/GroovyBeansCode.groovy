package com.doctor.groovy2_4_0

import java.time.LocalDateTime;

/**
 * @see http://groovy.codehaus.org/Groovy+Beans
 * @author doctor
 *
 * @since 2015年2月8日 下午4:20:32
 */
class GroovyBeansCode {
	Long id;
	String name;
	LocalDateTime birth;

	static main(args) {
		def groovyBean = new GroovyBeansCode(id:12L,name:"doctor",birth:LocalDateTime.now())
		println("Hello ${groovyBean.name}")
	}

}

package com.doctor.springframework.web.view.messageconverter;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author doctor
 *
 * @time   2015年1月9日 下午5:44:35
 */
@RestController
public class HttpMessageConverterController {

	@RequestMapping(value={"/test"},method=RequestMethod.GET,headers={"Accept=application/json, application/xml"})
	public PersonBean test() {
		return new PersonBean("doctor", "man", "alien");
	}
}

package com.github.doctor.experiment;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * spring 接口调用不受spring 管理类的方法
 * @author doctor
 *
 * @time   2014年12月24日 上午11:40:55
 */
public class Experiment1Test {
	
	@Test
	public void testReportInfo() {
		Experiment1 experiment = new Experiment1();
		experiment.prepare();
		
		String reportInfo = experiment.experimentService.reportInfo(experiment);
		assertNotNull(reportInfo);
		System.out.println(reportInfo);
	}

}

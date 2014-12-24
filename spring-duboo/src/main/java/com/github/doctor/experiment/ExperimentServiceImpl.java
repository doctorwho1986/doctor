package com.github.doctor.experiment;

import org.springframework.stereotype.Service;

@Service("experimentService")
public class ExperimentServiceImpl implements ExperimentService {

	@Override
	public String reportInfo(ReportInfo reportInfo) {
		return reportInfo.reportInfo();
	}


	

}

package com.doctor.kafkajstrom.log.service.imp;

import org.springframework.stereotype.Service;

import com.doctor.kafkajstrom.log.service.LogService;

@Service
public class LogServiceImp implements LogService {

	@Override
	public int getCount(String word) {
		return Integer.MAX_VALUE;
	}

}

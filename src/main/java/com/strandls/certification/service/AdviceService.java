package com.strandls.certification.service;

import com.strandls.certification.pojo.Advice;

public interface AdviceService {
	
	public Advice save(Advice advice);

	public Advice findById(Long id);
	
}

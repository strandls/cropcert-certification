package com.strandls.certification.service.imp;

import com.google.inject.Inject;
import com.strandls.certification.dao.AdviceDao;
import com.strandls.certification.pojo.Advice;
import com.strandls.certification.service.AbstractService;
import com.strandls.certification.service.AdviceService;

public class AdviceServiceImpl extends AbstractService<Advice> implements AdviceService {

	@Inject
	public AdviceServiceImpl(AdviceDao dao) {
		super(dao);
	}

}

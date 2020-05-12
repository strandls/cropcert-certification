package com.strandls.certification.service.imp;

import com.google.inject.Inject;
import com.strandls.certification.dao.FarmPlotDao;
import com.strandls.certification.pojo.FarmPlot;
import com.strandls.certification.service.AbstractService;
import com.strandls.certification.service.FarmPlotService;

public class FarmPlotServiceImpl extends AbstractService<FarmPlot> implements FarmPlotService {

	@Inject
	public FarmPlotServiceImpl(FarmPlotDao dao) {
		super(dao);
	}

}

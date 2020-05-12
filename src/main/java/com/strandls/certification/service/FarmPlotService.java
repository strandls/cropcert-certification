package com.strandls.certification.service;

import com.strandls.certification.pojo.FarmPlot;

public interface FarmPlotService {
	
	public FarmPlot save(FarmPlot farmPlot);

	public FarmPlot findById(Long id);
	
}

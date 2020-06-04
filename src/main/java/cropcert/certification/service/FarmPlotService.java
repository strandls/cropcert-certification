package cropcert.certification.service;

import cropcert.certification.pojo.FarmPlot;

public interface FarmPlotService {
	
	public FarmPlot save(FarmPlot farmPlot);

	public FarmPlot findById(Long id);
	
}

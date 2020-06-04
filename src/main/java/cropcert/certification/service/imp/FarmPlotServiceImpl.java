package cropcert.certification.service.imp;

import com.google.inject.Inject;
import cropcert.certification.dao.FarmPlotDao;
import cropcert.certification.pojo.FarmPlot;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.FarmPlotService;

public class FarmPlotServiceImpl extends AbstractService<FarmPlot> implements FarmPlotService {

	@Inject
	public FarmPlotServiceImpl(FarmPlotDao dao) {
		super(dao);
	}

}

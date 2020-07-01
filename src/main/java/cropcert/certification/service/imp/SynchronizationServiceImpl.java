package cropcert.certification.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cropcert.certification.dao.SynchronizationDao;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.SynchronizationService;
import cropcert.user.ApiException;
import cropcert.user.api.FarmerApi;
import cropcert.user.model.Farmer;

public class SynchronizationServiceImpl extends AbstractService<Synchronization> implements SynchronizationService {

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private FarmerApi farmerApi;
	
	@Inject
	private SynchronizationDao synchronizationDao;

	@Inject
	public SynchronizationServiceImpl(SynchronizationDao dao) {
		super(dao);
	}

	@Override
	public List<Synchronization> getSynchronizationForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) throws ApiException {
		Map<Long, FarmersInspectionReport> reports = new HashMap<Long, FarmersInspectionReport>();

		List<Farmer> farmers = new ArrayList<Farmer>();
		if (ccCode != -1) {
			farmers = farmerApi.getFarmerForCollectionCenter(ccCode, limit, offset);
		} else {
			farmers = farmerApi.findAll(limit, offset);
		}

		List<Long> farmerIds = new ArrayList<Long>();
		for (Farmer farmer : farmers) {
			Long id = farmer.getId();
			farmerIds.add(id);
		}

		List<Synchronization> synchronizations = synchronizationDao.getSynchronizationForCollectionCenter(limit, offset, farmerIds);

		return synchronizations;
	}

	@Override
	public Synchronization save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		Synchronization synchronization = objectMapper.readValue(jsonString, Synchronization.class);
		synchronization = save(synchronization);
		return synchronization;
	}
}

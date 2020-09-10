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
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.response.ICSFarmerList;
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
	public List<ICSFarmerList> getSynchronizationForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) throws ApiException {

		List<Farmer> farmers = new ArrayList<Farmer>();
		if (ccCode != -1) {
			farmers = farmerApi.getFarmerForCollectionCenter(ccCode, limit, offset);
		} else {
			farmers = farmerApi.findAll(limit, offset);
		}

		Map<Long, Farmer> farmerIdToFarmer = new HashMap<Long, Farmer>();
		for (Farmer farmer : farmers) {
			Long id = farmer.getId();
			farmerIdToFarmer.put(id, farmer);
		}

		List<Synchronization> synchronizations = synchronizationDao.getSynchronizationForCollectionCenter(limit, offset, farmerIdToFarmer.keySet());

		List<ICSFarmerList> icsFarmerLists = new ArrayList<ICSFarmerList>();
		for (Synchronization synchronization : synchronizations) {
			Farmer farmer = farmerIdToFarmer.get(synchronization.getFarmerId());
			ICSFarmerList icsFarmerList = new ICSFarmerList(farmer, synchronization);
			icsFarmerLists.add(icsFarmerList);
		}

		return icsFarmerLists;
	}

	@Override
	public Synchronization save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		Synchronization synchronization = objectMapper.readValue(jsonString, Synchronization.class);
		synchronization = save(synchronization);
		return synchronization;
	}

	@Override
	public Synchronization getReport(HttpServletRequest request, Integer version, Integer subVersion, Long farmerId) {
		Synchronization synchronization = synchronizationDao.getReport(version, subVersion, farmerId);
		return synchronization;
	}
	
	@Override
	public List<Synchronization> getRecentSubversionforFarmers(HttpServletRequest request, Integer version, Long farmerId) {
		List<Synchronization> synchronizations = synchronizationDao.getRecentSubversionEntry(version, farmerId);
		return synchronizations;
	}
}

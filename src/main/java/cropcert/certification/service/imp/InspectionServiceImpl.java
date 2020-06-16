package cropcert.certification.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import cropcert.certification.dao.InspectionDao;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.InspectionService;
import cropcert.user.ApiException;
import cropcert.user.api.FarmerApi;
import cropcert.user.model.Farmer;

public class InspectionServiceImpl extends AbstractService<Inspection> implements InspectionService {

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private InspectionDao inspectorDao;

	@Inject
	private FarmerApi farmerApi;

	@Inject
	public InspectionServiceImpl(InspectionDao dao) {
		super(dao);
	}

	@Override
	public Inspection findById(Long id) {
		Inspection inspection = super.findById(id);
		return inspection;
	}

	@Override
	public List<Inspection> findAll(HttpServletRequest request, Integer limit, Integer offset) {
		return findAll(limit, offset);
	}

	@Override
	public Inspection save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException {
		Inspection inspection = objectMapper.readValue(jsonString, Inspection.class);
		inspection = save(inspection);
		return inspection;
	}

	@Override
	public List<Inspection> getReportsForInspector(HttpServletRequest request, Integer limit, Integer offset,
			Long inspectorId, Long farmerId) {
		return inspectorDao.getReportsForInspector(limit, offset, inspectorId, farmerId);
	}

	@Override
	public Collection<FarmersInspectionReport> getReportsForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) {

		Map<Long, FarmersInspectionReport> reports = new HashMap<Long, FarmersInspectionReport>();

		List<Farmer> farmers = new ArrayList<Farmer>();
		try {
			if (ccCode != -1) {
				farmers = farmerApi.getFarmerForCollectionCenter(ccCode, limit, offset);
			} else {
				farmers = farmerApi.findAll(limit, offset);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}

		List<Long> farmerIds = new ArrayList<Long>();
		for (Farmer farmer : farmers) {
			Long id = farmer.getId();
			farmerIds.add(id);
			FarmersInspectionReport farmersLastReport = new FarmersInspectionReport(farmer, null);
			reports.put(id, farmersLastReport);
		}

		List<Inspection> inspections = inspectorDao.getReportsForCollectionCenter(limit, offset, farmerIds);

		for (Inspection inspection : inspections) {
			Long id = inspection.getFarmerId();
			FarmersInspectionReport farmersLastReport = reports.get(id);
			farmersLastReport.setInspection(inspection);
		}

		return reports.values();
	}

	@Override
	public FarmersInspectionReport getLatestFarmerReport(HttpServletRequest request, Long farmerId)
			throws ApiException {
		Farmer farmer = farmerApi.find(farmerId);
		List<Inspection> inspections = inspectorDao.getByPropertyWithCondtion("farmerId", farmer.getId(), "=", -1, -1);
		if (inspections == null || inspections.size() == 0)
			return new FarmersInspectionReport(farmer, null);

		Inspection latestInspection = inspections.get(0);

		for (int i = 1; i < inspections.size(); i++) {
			Inspection inspection = inspections.get(i);
			if (inspection.getDate().after(latestInspection.getDate()))
				latestInspection = inspection;
		}
		return new FarmersInspectionReport(farmer, latestInspection);
	}

	@Override
	public List<FarmersInspectionReport> getAllFarmerReport(HttpServletRequest request, Long farmerId)
			throws ApiException {
		Farmer farmer = farmerApi.find(farmerId);
		List<Inspection> inspections = inspectorDao.getByPropertyWithCondtion("farmerId", farmer.getId(), "=", -1, -1);

		List<FarmersInspectionReport> reports = new ArrayList<FarmersInspectionReport>();
		for (Inspection inspection : inspections) {
			FarmersInspectionReport report = new FarmersInspectionReport(farmer, inspection);
			reports.add(report);
		}
		return reports;
	}

}

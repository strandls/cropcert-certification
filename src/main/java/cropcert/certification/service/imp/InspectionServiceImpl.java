package cropcert.certification.service.imp;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.pac4j.core.profile.CommonProfile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import cropcert.certification.dao.InspectionDao;
import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.Signature;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.request.ICSSignRequest;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.certification.service.AbstractService;
import cropcert.certification.service.InspectionService;
import cropcert.certification.service.SynchronizationService;
import cropcert.certification.util.UserUtil;
import cropcert.user.ApiException;
import cropcert.user.api.FarmerApi;
import cropcert.user.api.UserApi;
import cropcert.user.model.Farmer;
import cropcert.user.model.User;

public class InspectionServiceImpl extends AbstractService<Inspection> implements InspectionService {

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private InspectionDao inspectorDao;

	@Inject
	private UserApi userApi;

	@Inject
	private FarmerApi farmerApi;

	@Inject
	private InspectionService inspectionService;

	@Inject
	private SynchronizationService synchronizationService;

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
	public FarmersInspectionReport save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException, ApiException {
		jsonString = "[" + jsonString + "]";
		return bulkUpload(request, jsonString).get(0);
	}

	@Override
	public List<FarmersInspectionReport> bulkUpload(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException, ApiException {
		JSONArray jsonArray = new JSONArray(jsonString);
		List<FarmersInspectionReport> farmersInspectionReports = new ArrayList<FarmersInspectionReport>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Inspection inspection = objectMapper.readValue(jsonObject.toString(), Inspection.class);

			Long farmerId = inspection.getFarmerId();
			String inspectorIdString = UserUtil.getUserDetails(request).getId();
			Long inspectorId = Long.parseLong(inspectorIdString);

			FarmersInspectionReport farmersInspectionReport = inspectionService.getLatestFarmerReport(request,
					farmerId);

			Long updatedBy = inspectorId;

			Integer version;
			Integer subVersion;
			Boolean isReportFinalized = false;
			Timestamp lastUpdated = new Timestamp(new Date().getTime());
			Boolean isDeleted = false;

			if (farmersInspectionReport.getInspection() == null) {
				version = 0;
				subVersion = 1;
			} else { // Will definitely have the inspection report otherwise previous condition is
						// false.
				Long inspectionId = farmersInspectionReport.getInspection().getId();
				List<Synchronization> syncs = synchronizationService.getByPropertyWithCondtion("reportId", inspectionId,
						"=", -1, -1);

				Synchronization synchronization = syncs.get(0);
				if (synchronization.getIsReportFinalized()) {
					version = synchronization.getVersion() + 1;
					subVersion = 1;
				} else {
					version = synchronization.getVersion();
					subVersion = synchronization.getSubVersion() + 1;
				}
			}

			inspection = save(inspection);
			Synchronization synchronization = new Synchronization(null, farmerId, inspection.getId(), version,
					subVersion, isReportFinalized, lastUpdated, updatedBy, isDeleted);
			synchronizationService.save(synchronization);

			farmersInspectionReport.setInspection(inspection);
			farmersInspectionReports.add(farmersInspectionReport);
		}
		return farmersInspectionReports;
	}

	@Override
	public List<FarmersInspectionReport> getAllReportsOfFarmer(HttpServletRequest request, Long farmerId)
			throws ApiException {
		Farmer farmer = farmerApi.find(farmerId);
		List<Inspection> inspections = inspectorDao.getByPropertyWithCondtion("farmerId", farmer.getId(), "=", -1, -1);

		List<FarmersInspectionReport> reports = new ArrayList<FarmersInspectionReport>();
		for (Inspection inspection : inspections) {
			Long inspectionId = inspection.getId();
			Synchronization syncs = synchronizationService.findByPropertyWithCondtion("reportId",
					inspectionId.toString(), "=");

			FarmersInspectionReport report = new FarmersInspectionReport(farmer, syncs.getVersion(),
					syncs.getSubVersion(), inspection);
			reports.add(report);
		}
		return reports;
	}

	@Override
	public List<Inspection> getReportsForInspector(HttpServletRequest request, Integer limit, Integer offset,
			Long inspectorId, Long farmerId) {
		return inspectorDao.getReportsForInspector(limit, offset, inspectorId, farmerId);
	}

	@Override
	public Collection<FarmersInspectionReport> getReportsForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode) {

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
		return getLatestReportForFarmers(farmers, limit, offset);
	}

	@Override
	public FarmersInspectionReport getLatestFarmerReport(HttpServletRequest request, Long farmerId)
			throws ApiException {

		Farmer farmer = farmerApi.find(farmerId);
		List<Farmer> farmers = new ArrayList<Farmer>();
		farmers.add(farmer);
		Collection<FarmersInspectionReport> reports = getLatestReportForFarmers(farmers, -1, -1);
		return reports.iterator().next();
	}

	private Collection<FarmersInspectionReport> getLatestReportForFarmers(List<Farmer> farmers, Integer limit,
			Integer offset) {
		Map<Long, FarmersInspectionReport> reports = new HashMap<Long, FarmersInspectionReport>();
		List<Long> farmerIds = new ArrayList<Long>();
		for (Farmer farmer : farmers) {
			Long id = farmer.getId();
			farmerIds.add(id);
			FarmersInspectionReport farmersLastReport = new FarmersInspectionReport(farmer, 0, 0, null);
			reports.put(id, farmersLastReport);
		}

		List<Inspection> inspections = inspectorDao.getLatestReportForFarmers(limit, offset, farmerIds);

		for (Inspection inspection : inspections) {
			Long inspectionId = inspection.getId();
			List<Synchronization> syncs = synchronizationService.getByPropertyWithCondtion("reportId", inspectionId,
					"=", -1, -1);
			Long id = inspection.getFarmerId();
			FarmersInspectionReport farmersLastReport = reports.get(id);
			farmersLastReport.setInspection(inspection);
			farmersLastReport.setVersion(syncs.get(0).getVersion());
			farmersLastReport.setSubVersion(syncs.get(0).getSubVersion());
		}

		return reports.values();
	}

	@Override
	public FarmersInspectionReport signByICSManager(HttpServletRequest request, ICSSignRequest icsSignRequest) throws ApiException {
		
		Long farmerId = icsSignRequest.getFarmerId();
		Integer version = icsSignRequest.getVersion();
		Integer subVersion = icsSignRequest.getSubVersion();

		CommonProfile profile = UserUtil.getUserDetails(request);
		User user = userApi.find(Long.parseLong(profile.getId()));
		String icsManagerName = user.getFirstName();
		String icsManagerSignPath = user.getSign();
		
		return signSingleReport(request, farmerId, version, subVersion, icsManagerName, icsManagerSignPath);
	}

	@Override
	public List<FarmersInspectionReport> bulkReportsSignByICSManager(HttpServletRequest request, List<ICSSignRequest> icsSignRequests) throws NumberFormatException, ApiException {
		CommonProfile profile = UserUtil.getUserDetails(request);
		User user = userApi.find(Long.parseLong(profile.getId()));
		String icsManagerName = user.getFirstName();
		String icsManagerSignPath = user.getSign();
		
		List<FarmersInspectionReport> inspections = new ArrayList<FarmersInspectionReport>();
		for(ICSSignRequest icsSignRequest : icsSignRequests) {
			Long farmerId = icsSignRequest.getFarmerId();
			Integer version = icsSignRequest.getVersion();
			Integer subVersion = icsSignRequest.getSubVersion();
			inspections.add(signSingleReport(request, farmerId, version, subVersion, icsManagerName, icsManagerSignPath));
		}
		
		return inspections;
	}
	
	private FarmersInspectionReport signSingleReport(HttpServletRequest request, Long farmerId, Integer version, Integer subVersion, String icsManagerName, String icsManagerSignPath) throws ApiException {
		Synchronization syncEntry = synchronizationService.getReport(request, version, subVersion, farmerId);
		
		Timestamp currentTime = new Timestamp(new Date().getTime());
		
		syncEntry.setVersion(version+1);
		syncEntry.setSubVersion(0);
		syncEntry.setLastUpdated(currentTime);

		Long inspectionId = syncEntry.getReportId();
		Inspection inspection = inspectionService.findById(inspectionId);
		Signature icsManagerSign = inspection.getIcsManager();
		if(icsManagerSign == null) {
			icsManagerSign = new Signature();
		}
		icsManagerSign.setDate(currentTime);
		icsManagerSign.setDone(true);
		icsManagerSign.setName(icsManagerName);
		icsManagerSign.setPath(icsManagerSignPath);
		
		List<Synchronization> prevSyncVersions = synchronizationService.getRecentSubversionforFarmers(request, version, farmerId);
		for(Synchronization prevSyncVersion : prevSyncVersions) {
			prevSyncVersion.setIsReportFinalized(true);
			synchronizationService.update(prevSyncVersion);
		}
	
		syncEntry.setIsReportFinalized(true);
		synchronizationService.update(syncEntry);
		
		inspection.setIcsManager(icsManagerSign);
		inspection = update(inspection);
		
		Farmer farmer = farmerApi.find(farmerId);
		FarmersInspectionReport inspectionReport = new FarmersInspectionReport(farmer, version, subVersion, inspection);
		
		return inspectionReport;
	}	
}

package cropcert.certification.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.response.FarmersInspectionReport;
import cropcert.user.ApiException;

public interface InspectionService {

	public List<Inspection> findAll(HttpServletRequest request, Integer limit, Integer offset);

	public FarmersInspectionReport save(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException, ApiException;

	public Inspection findById(Long id);

	public List<Inspection> getReportsForInspector(HttpServletRequest request, Integer limit, Integer offset,
			Long inspectorId, Long farmerId);

	public Collection<FarmersInspectionReport> getReportsForCollectionCenter(HttpServletRequest request, Integer limit,
			Integer offset, Long ccCode);

	public FarmersInspectionReport getLatestFarmerReport(HttpServletRequest request, Long farmerId) throws ApiException;

	public List<FarmersInspectionReport> getAllReportsOfFarmer(HttpServletRequest request, Long farmerId)
			throws ApiException;

	public List<FarmersInspectionReport> bulkUpload(HttpServletRequest request, String jsonString)
			throws JsonParseException, JsonMappingException, IOException, ApiException;

	public FarmersInspectionReport signByICSManager(HttpServletRequest request, String jsonString) throws ApiException;

}

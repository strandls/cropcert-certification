package cropcert.certification.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cropcert.certification.pojo.Inspection;
import cropcert.certification.pojo.response.FarmersInspectionReport;

public interface InspectionService {
	
	public List<Inspection> findAll(HttpServletRequest request, Integer limit, Integer offset);

	public Inspection save(HttpServletRequest request, String jsonString) throws JsonParseException, JsonMappingException, IOException;

	public Inspection findById(Long id);

	public List<Inspection> getReportsForInspector(HttpServletRequest request, Integer limit, Integer offset, Long inspectorId, Long farmerId);

	public Collection<FarmersInspectionReport> getReportsForCollectionCenter(HttpServletRequest request, Integer limit, Integer offset,
			Long ccCode, Long farmerId);
	
}

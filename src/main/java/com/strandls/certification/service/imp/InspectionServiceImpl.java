package com.strandls.certification.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.strandls.certification.dao.InspectionDao;
import com.strandls.certification.pojo.Inspection;
import com.strandls.certification.service.AbstractService;
import com.strandls.certification.service.InspectionService;

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
	public List<Inspection> getReportsForCollectionCenter(HttpServletRequest request, Integer limit, Integer offset,
			Long ccCode, Long farmerId) {
		List<Farmer> farmers = new ArrayList<Farmer>();
		try {
			farmers = farmerApi.getFarmerForCollectionCenter(ccCode, limit, offset);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Long> farmerIds = new ArrayList<Long>();
		if (farmerId != -1)
			farmerIds.add(farmerId);
		else
			for (Farmer farmer : farmers) {
				farmerIds.add(farmer.getId());
			}

		return inspectorDao.getReportsForCollectionCenter(limit, offset, ccCode, farmerIds);
	}

}

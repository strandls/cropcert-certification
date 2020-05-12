package com.strandls.certification.service.imp;

import java.io.IOException;
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

public class InspectionServiceImpl extends AbstractService<Inspection> implements InspectionService{

	@Inject
	private ObjectMapper objectMapper;
	
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
	public Inspection save(HttpServletRequest request, String jsonString) throws JsonParseException, JsonMappingException, IOException {
		Inspection inspection = objectMapper.readValue(jsonString, Inspection.class);
		inspection = save(inspection);
		return inspection;
	}

}

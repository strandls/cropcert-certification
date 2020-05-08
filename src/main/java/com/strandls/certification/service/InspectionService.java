package com.strandls.certification.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.strandls.certification.pojo.Inspection;

public interface InspectionService {
	
	public List<Inspection> findAll(HttpServletRequest request, Integer limit, Integer offset);

	public Inspection save(HttpServletRequest request, String jsonString) throws JsonParseException, JsonMappingException, IOException;
	
}

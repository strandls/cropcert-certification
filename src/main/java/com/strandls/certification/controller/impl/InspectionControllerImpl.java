package com.strandls.certification.controller.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.certification.controller.InspectionController;
import com.strandls.certification.pojo.Inspection;
import com.strandls.certification.service.InspectionService;

public class InspectionControllerImpl implements InspectionController{

	@Inject
	private InspectionService inspectionService;
	
	@Override
	public Response ping()  {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@Override
	public Response findById(HttpServletRequest request, Long id) {
		try {
			Inspection inspection = inspectionService.findById(id);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response findAll(HttpServletRequest request, Integer limit, Integer offset) {
		try {
			List<Inspection> inspections = inspectionService.findAll(request, limit, offset);
			return Response.ok().entity(inspections).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response addInspection(HttpServletRequest request, String jsonString) {
		try {
			Inspection inspection = inspectionService.save(request, jsonString);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
}

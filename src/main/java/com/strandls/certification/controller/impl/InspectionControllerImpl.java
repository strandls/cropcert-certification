package com.strandls.certification.controller.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.strandls.certification.controller.InspectionController;
import com.strandls.certification.filter.Permissions;
import com.strandls.certification.filter.TokenAndUserAuthenticated;
import com.strandls.certification.pojo.Inspection;
import com.strandls.certification.pojo.response.FarmersLastReport;
import com.strandls.certification.service.InspectionService;

public class InspectionControllerImpl implements InspectionController {

	@Inject
	private InspectionService inspectionService;

	@Override
	public Response ping() {
		return Response.status(Status.OK).entity("PONG").build();
	}

	@Override
	public Response findById(@Context HttpServletRequest request, Long id) {
		try {
			Inspection inspection = inspectionService.findById(id);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	public Response findAllByCCCode(@Context HttpServletRequest request, Integer limit, Integer offset, Long ccCode,
			Long farmerId) {
		try {
			Map<Long, FarmersLastReport> reports = inspectionService.getReportsForCollectionCenter(request, limit, offset, ccCode, farmerId);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response addInspection(@Context HttpServletRequest request, String jsonString) {
		try {
			Inspection inspection = inspectionService.save(request, jsonString);
			return Response.ok().entity(inspection).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
}

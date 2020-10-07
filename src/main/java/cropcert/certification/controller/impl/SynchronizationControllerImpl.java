package cropcert.certification.controller.impl;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import cropcert.certification.controller.SynchronizationController;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.pojo.response.ICSFarmerList;
import cropcert.certification.service.SynchronizationService;

public class SynchronizationControllerImpl implements SynchronizationController {

	@Inject
	private SynchronizationService synchronizationService;
	
	@Override
	public Response getAllByCCCode(HttpServletRequest request, Integer limit, Integer offset, String ccCodes, Boolean pendingReportOnly, String firstName) {
		try {
			List<ICSFarmerList> reports = synchronizationService.getSynchronizationForCollectionCenter(request, limit, offset, ccCodes, pendingReportOnly, firstName);
			return Response.ok().entity(reports).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response addSyncEntry(HttpServletRequest request, String jsonString) {
		try {
			Synchronization synchronization = synchronizationService.save(request, jsonString);
			return Response.ok().entity(synchronization).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}

	@Override
	public Response getReport(HttpServletRequest request, Integer version, Integer subVersion, Long farmerId) {
		try {
			Synchronization synchronization = synchronizationService.getReport(request, version, subVersion, farmerId);
			return Response.ok().entity(synchronization).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
	
	@Override
	public Response getReport(HttpServletRequest request, Integer version, Long farmerId) {
		try {
			List<Synchronization> synchronizations  = synchronizationService.getRecentSubversionforFarmers(request, version, farmerId);
			return Response.ok().entity(synchronizations).build();
		} catch (Exception e) {
			throw new WebApplicationException(
					Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build());
		}
	}
}

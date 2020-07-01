package cropcert.certification.controller.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import cropcert.certification.controller.SynchronizationController;
import cropcert.certification.pojo.Synchronization;
import cropcert.certification.service.SynchronizationService;

public class SynchronizationControllerImpl implements SynchronizationController {

	@Inject
	private SynchronizationService synchronizationService;
	
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

}

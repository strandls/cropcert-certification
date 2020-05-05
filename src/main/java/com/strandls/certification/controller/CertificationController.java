/**
 * 
 */
package com.strandls.certification.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.strandls.certification.ApiConstants;

/**
 * @author Abhishek Rudra
 *
 */
@Path(ApiConstants.V1 + ApiConstants.SERIVCE)
public class CertificationController {

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)

	public Response ping() {
		return Response.status(Status.OK).entity("PONG").build();
	}

}

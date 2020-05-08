/**
 * 
 */
package com.strandls.certification.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.strandls.certification.ApiConstants;
import com.strandls.certification.pojo.Inspection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vilay
 *
 */
@Path(ApiConstants.CERTIFICATION)
@Api("Certification")
public interface CertificationController {

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping();

	@Path("all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report", response = Inspection.class, responseContainer = "List")
	public Response findAll(@Context HttpServletRequest request, @DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset);
	
	@POST
	@Path("inspection")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add inspection report", notes = "Returns succuess failure", response = Inspection.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Could not add inspection report", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	public Response addInspection(@Context HttpServletRequest request, String jsonString);
}

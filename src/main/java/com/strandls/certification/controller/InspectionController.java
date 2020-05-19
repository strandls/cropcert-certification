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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.strandls.certification.ApiConstants;
import com.strandls.certification.filter.Permissions;
import com.strandls.certification.filter.TokenAndUserAuthenticated;
import com.strandls.certification.pojo.Inspection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author vilay
 *
 */
@Path(ApiConstants.INSPECTION)
@Api("Inspection")
public interface InspectionController {

	@GET
	@Path(ApiConstants.PING)
	@Produces(MediaType.TEXT_PLAIN)
	public Response ping();

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report", response = Inspection.class, responseContainer = "List")
	public Response findById(@Context HttpServletRequest request, @PathParam("id") Long id);

	@Path("all/{inspectorId}/{farmerId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the inspection report", response = Inspection.class, responseContainer = "List")
	public Response findAll(@Context HttpServletRequest request, @DefaultValue("-1") @QueryParam("limit") Integer limit,
			@DefaultValue("-1") @QueryParam("offset") Integer offset,
			@DefaultValue("-1") @QueryParam("inspectorId") Long inspectorId,
			@DefaultValue("-1") @QueryParam("farmerId") Long farmerId);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add inspection report", notes = "Returns succuess failure", response = Inspection.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Could not add inspection report", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response addInspection(@Context HttpServletRequest request, String jsonString);
}

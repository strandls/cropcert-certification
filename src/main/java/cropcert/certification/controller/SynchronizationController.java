/**
 * 
 */
package cropcert.certification.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cropcert.certification.ApiConstants;
import cropcert.certification.filter.Permissions;
import cropcert.certification.filter.TokenAndUserAuthenticated;
import cropcert.certification.pojo.Inspection;
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
@Path(ApiConstants.SYNC)
@Api("Synchronization")
public interface SynchronizationController {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Add sync entry", notes = "Returns succuess failure", response = Inspection.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Add the sync entry", response = String.class),
			@ApiResponse(code = 500, message = "ERROR", response = String.class) })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
	@TokenAndUserAuthenticated(permissions = { Permissions.INSPECTOR })
	public Response addSyncEntry(@Context HttpServletRequest request, String jsonString);
}

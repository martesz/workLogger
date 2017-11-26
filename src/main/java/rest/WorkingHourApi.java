package rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import authentication.Secured;
import authentication.UserSecurityContext;
import entities.DebugLogger;
import entities.Issue;
import entities.User;
import entities.WorkingHour;
import service.WorkingHourService;

@Path("/hour")
@Stateless
public class WorkingHourApi {
	public static final DebugLogger logger = new DebugLogger(WorkingHour.class.getName());

	@EJB
	WorkingHourService hourService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response addWorkingHour(@Context ContainerRequestContext securityContext, WorkingHour workingHour) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		workingHour.setUser(user);
		logger.log("Adding to database: " + workingHour);
		hourService.addWorkingHour(workingHour);
		return Response.ok(workingHour).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response updateWorkingHour(WorkingHour workingHour) {
		hourService.updateWorkingHour(workingHour);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Secured
	public Response removeWorkingHour(@PathParam(value = "id") long id) {
		WorkingHour workingHour = hourService.getWorkingHourById(Long.valueOf(id));
		if (workingHour == null) {
			logger.log("Not removing from database, because WorkingHour not exists with id: " + id);
			return Response.notModified().build();
		}
		logger.log("Removing from database: " + workingHour);
		hourService.removeWorkingHour(workingHour);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response getWorkingHoursByUser(@Context ContainerRequestContext securityContext) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		List<WorkingHour> workingHours = hourService.getWorkingHours(user);
		if (workingHours == null) {
			workingHours = new ArrayList<>();
		}
		return Response.ok(workingHours).build();
	}

	@GET
	@Path("/byId")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkingHoursByUserId(@QueryParam(value = "userId") String userId) {
		List<WorkingHour> workingHours = hourService.getWorkingHourByUserId(userId);
		if (workingHours == null) {
			workingHours = Collections.emptyList();
		}
		return Response.ok(workingHours).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/issues")
	@Secured
	public Response getIssues() {
		List<Issue> issues = hourService.getIssues();
		if (issues == null) {
			issues = new ArrayList<>();
			logger.log("No issues found. Returning empty list.");
		} else {
			logger.log("Issues found, returning count: " + issues.size());
		}
		return Response.ok(issues).build();
	}
}

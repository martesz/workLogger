package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import authentication.Secured;
import authentication.UserSecurityContext;
import entities.User;
import entities.WorkingHour;
import service.WorkingHourService;

@Path("/hour")
@Stateless
public class WorkingHourApi {

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
		hourService.addWorkingHour(workingHour);
		return Response.ok("Working hour added to user: " + user.getName() + "with start: " + workingHour.getStarting()
				+ " with duration: " + workingHour.getDuration()).build();
	}
	
	@Secured
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateWorkingHour(@Context ContainerRequestContext securityContext, WorkingHour workingHour) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		hourService.updateWorkingHour(workingHour);
		return Response.ok("Working hour updated to user: " + user.getName() + "with start: " + workingHour.getStarting()
		+ " with duration: " + workingHour.getDuration()).build();
	}
	
	@Secured
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeWorkingHour(@Context ContainerRequestContext securityContext, WorkingHour workingHour) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		hourService.removeWorkingHour(workingHour);
		return Response.ok("Working hour removed to user: " + user.getName() + "with start: " + workingHour.getStarting()
		+ " with duration: " + workingHour.getDuration()).build();
	}
	
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorkingHoursByUser(@Context ContainerRequestContext securityContext) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		List<WorkingHour> workingHours = hourService.getWorkingHours(user);
		if (workingHours == null) {
			workingHours = new ArrayList<>();
		}
		return Response.ok(workingHours).build();
	}
}

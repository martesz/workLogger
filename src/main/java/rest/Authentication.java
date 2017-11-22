package rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import authentication.Secured;
import authentication.UserSecurityContext;
import entities.User;
import entities.User.Level;
import service.UserService;

@Stateless
@Path("/auth")
public class Authentication {
	@EJB
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response login(@Context ContainerRequestContext securityContext) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User user = userSecurityContext.getUser();
		User registered = userService.registerOrLoginUser(user);
		return Response.ok(registered).build();
	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	@Secured
	@Path("/{googleId}")
	public Response setUserLevel(@Context ContainerRequestContext securityContext, @PathParam(value = "googleId") String googleId, String level) {
		UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		User issuer = userSecurityContext.getUser();
		Level levelOfIssuer = userService.getLevelOfUser(issuer.getGoogleId());
		if (levelOfIssuer == Level.ADMIN) {
			userService.updateLevel(googleId, level);
			return Response.ok("User level updated to " + level).build();
		} else {
			return Response.ok("User level not updated, needs admin level").build();
		}
	}
}

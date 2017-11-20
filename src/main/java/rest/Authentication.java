package rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

	@Context
	private UserSecurityContext securityContext;

	@EJB
	private UserService userService;

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response login() {
		User user = securityContext.getUser();
		User registered = userService.registerOrLoginUser(user);
		return Response.ok(registered).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	@Path("/{googleId}")
	public Response setUserLevel(@PathParam(value = "googleId") String googleId, Level level) {
		User issuer = securityContext.getUser();
		Level levelOfIssuer = userService.getLevelOfUser(issuer.getGoogleId());
		if (levelOfIssuer == Level.ADMIN) {
			userService.updateLevel(googleId, level);
			return Response.ok("User level updated to " + level).build();
		} else {
			return Response.ok("User level not updated, needs admin level").build();
		}
	}
}

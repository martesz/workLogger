package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAllowedException;
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
import entities.DebugLogger;
import entities.User;
import entities.User.Level;
import service.UserService;

@Stateless
@Path("/auth")
public class Authentication {
	public static final DebugLogger logger = new DebugLogger(Authentication.class.getName());
	
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
			logger.log("User with googleId " + googleId + " updated to level " + level + " by issuer " + issuer);
			return Response.ok("User level updated to " + level).build();
		} else {
			logger.log("User with googleId " + googleId + " cannot be updated to level " + level + ", because issuer level is not admin: " + issuer);
			return Response.ok("User level not updated, needs admin level").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	@Secured
	public Response getUsers(@Context ContainerRequestContext securityContext) {
		final UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		final User requester = userSecurityContext.getUser();
		final Level levelOfRequester = userService.getLevelOfUser(requester.getGoogleId());
		if (levelOfRequester != Level.ADMIN && levelOfRequester != Level.PROJECT_LEADER) {
			logger.log("Users cannot be returned, because requester level is not project leader or admin: " + requester);
			throw new NotAllowedException(Response.ok("Requester is not project leader or admin").build());
		}
		List<User> users = userService.getUsers();
		if (users == null) {
			users = new ArrayList<>();
		}
		return Response.ok(users).build();
	}

}

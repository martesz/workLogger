package rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import authentication.Secured;
import authentication.UserSecurityContext;
import entities.User;
import service.UserService;

@Stateless
@Path("/auth")
public class Authentication {

	@Context
	private UserSecurityContext securityContext;

	@EJB
	private UserService userService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response registerNewUser(User.Level level) {
		User user = securityContext.getUser();
		user.setLevel(level);
		userService.registerNewUser(user);
		return Response.ok("User registered with name: " + user.getName()).build();
	}
}

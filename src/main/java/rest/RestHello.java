package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RestHello {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response hello() {
		String hello = "Hello world!";
		return Response.ok(hello).build();
	}
}

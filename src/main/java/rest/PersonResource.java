package rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Person;
import service.PersonService;

@Path("/person")
@Stateless
public class PersonResource {

	@EJB
	PersonService personService;

	@GET
	@Path("/add")
	@Produces(MediaType.TEXT_HTML)
	public Response addPerson(@QueryParam("name") String name, @QueryParam("position") String position) {
		personService.addNewPerson(name, position);

		return Response.ok("Add new person called with name: " + name + " position: " + position).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersons() {
		List<Person> persons = personService.getPersons();
		return Response.ok(persons).build();
	}
}

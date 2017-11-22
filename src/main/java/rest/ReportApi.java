package rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import authentication.Secured;
import entities.Project;
import service.ReportService;
import service.WorkingHourService;

@Path("/report")
public class ReportApi {

	@EJB
	private ReportService reportService;
	@EJB
	private WorkingHourService workingHourService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	@Path("/{projectName}")
	public Response getReportByProjectName(@PathParam(value = "projectName") String projectName) {
		Project project = reportService.getProjectReport(projectName);
		return Response.ok(project).build();
	}

}

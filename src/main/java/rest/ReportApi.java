package rest;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import authentication.UserSecurityContext;
import entities.Project;
import service.ReportService;

@Path("/report")
public class ReportApi {

	@Context
	private UserSecurityContext securityContext;

	@EJB
	private ReportService reportService;

	@Path("/{projectName}")
	public Response getReportByProjectName(@PathParam(value = "projectName") String projectName) {
		Project project = reportService.getProjectReport(projectName);
		return Response.ok(project).build();
	}

}

package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
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
import entities.Report;
import entities.User;
import entities.User.Level;
import entities.WorkingHour;
import service.ReportService;
import service.UserService;
import service.WorkingHourService;

@Path("/report")
public class ReportApi {
	public static final DebugLogger logger = new DebugLogger(WorkingHour.class.getName());

	@EJB
	private UserService userService;
	@EJB
	private ReportService reportService;
	@EJB
	private WorkingHourService workingHourService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Secured
	public Response addReport(@Context ContainerRequestContext securityContext, Report report) {
		final UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		final User user = userSecurityContext.getUser();
		report.setOwner(user);
		logger.log("Adding to database: " + report);
		reportService.addReport(report);
		return Response.ok(report).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Secured
	public Response updateReport(Report report) {
		reportService.updateReport(report);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	@Secured
	public Response removeReport(@PathParam(value = "id") long id) {
		Report report = reportService.getReportById(Long.valueOf(id));
		if (report == null) {
			logger.log("Not removing from database, because Report not exists with id: " + id);
			return Response.notModified().build();
		}
		logger.log("Removing from database: " + report);
		reportService.removeReport(report);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{reportId}")
	@Secured
	public Response getReportById(@PathParam(value = "reportId") long reportId) {
		final Report report = reportService.getReportById(reportId);
		return Response.ok(report).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	@Secured
	public Response getReportsByUser(@Context ContainerRequestContext securityContext) {
		final UserSecurityContext userSecurityContext = (UserSecurityContext) securityContext.getSecurityContext();
		final User user = userSecurityContext.getUser();
		final User owner = userService.getUserByGoogleId(user.getGoogleId());
		logger.log("getting reports for database user " + owner);
		List<Report> reports;
		if (owner.getLevel() == Level.ADMIN) {
			logger.log("user is admin, getting all reports");
			reports = reportService.getAllReports();
		} else {
			reports = reportService.getReports(owner);
		}
		if (reports == null) {
			reports = new ArrayList<>();
		}
		return Response.ok(reports).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportsByUserId(@QueryParam(value="userId") String userId) {
		List<Report> reports = reportService.getReports(userId);
		if (reports == null) {
			reports = new ArrayList<>();
		}
		return Response.ok(reports).build();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/sum/{reportId}")
	@Secured
	public Response getWorkedHoursForReport(@PathParam(value = "reportId") long reportId) {
		final Report report = reportService.getReportById(reportId);
		List<WorkingHour> workingHours = reportService.getWorkingHoursForReport(report);
		long sumTime = 0;
		if (workingHours != null) {
			for (WorkingHour hour: workingHours) {
				sumTime += hour.getDuration();
			}
		}
		return Response.ok(sumTime).build();
	}
}

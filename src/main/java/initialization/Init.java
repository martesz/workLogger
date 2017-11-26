package initialization;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import database.ReportDao;
import entities.DebugLogger;
import entities.Issue;
import entities.Project;
import entities.User;
import entities.User.Level;
import entities.WorkingHour;
import service.ProjectIssueService;
import service.UserService;
import service.WorkingHourService;

@Startup
@Singleton
public class Init {
	public static final DebugLogger logger = new DebugLogger(ReportDao.class.getName());

	@EJB
	private ProjectIssueService projectIssueService;
	@EJB
	private UserService userService;
	@EJB
	private WorkingHourService workingHourService;

	@PostConstruct
	public void init() {
		logger.log("started initialization");
		logger.log("initializing users...");

		final User adminUser = new User();
		adminUser.setGoogleId("101812010762749781224");
		adminUser.setLevel(Level.ADMIN);
		adminUser.setName("Imre Admin");
		userService.addUser(adminUser);

		final User projectLeaderUser = new User();
		projectLeaderUser.setGoogleId("101498757619441151086");
		projectLeaderUser.setLevel(Level.PROJECT_LEADER);
		projectLeaderUser.setName("Imre Project Leader");
		userService.addUser(projectLeaderUser);

		final User deletableUser = new User();
		deletableUser.setGoogleId("000000000");
		deletableUser.setLevel(Level.EMPLOYEE);
		deletableUser.setName("Deletable");
		userService.addUser(deletableUser);

		logger.log("users initialized successfully.");
		logger.log("initializing projects...");

		final Project project1 = new Project();
		project1.setName("Project 1");
		project1.setDescription("Description of project 1");
		projectIssueService.addProject(project1);

		final Project project2 = new Project();
		project2.setName("Project 2");
		project2.setDescription("Description of project 2");
		projectIssueService.addProject(project2);

		final Project project3 = new Project();
		project3.setName("Project 3");
		project3.setDescription("Description of project 3");
		projectIssueService.addProject(project3);

		logger.log("projects initialized successfully.");
		logger.log("initializing issues...");

		final Issue issue1 = new Issue();
		issue1.setName("Issue 1");
		issue1.setDescription("Description of issue 1");
		issue1.setProject(project1);
		projectIssueService.addIssue(issue1);

		final Issue issue2 = new Issue();
		issue2.setName("Issue 2");
		issue2.setDescription("Description of issue 2");
		issue2.setProject(project1);
		projectIssueService.addIssue(issue2);

		final Issue issue3 = new Issue();
		issue3.setName("Issue 3");
		issue3.setDescription("Description of issue 3");
		issue3.setProject(project2);
		projectIssueService.addIssue(issue3);

		final Issue issue4 = new Issue();
		issue4.setName("Issue 4");
		issue4.setDescription("Description of issue 4");
		issue4.setProject(project3);
		projectIssueService.addIssue(issue4);

		logger.log("issues initialized successfully.");
		logger.log("initializing working hours...");

		final WorkingHour workingHour1 = new WorkingHour();
		workingHour1.setUser(adminUser);
		workingHour1.setIssue(issue1);
		workingHour1.setStarting(createStartingDate("2017-11-24 10:00"));
		workingHour1.setDuration(createDuration(5, 20, 0));
		workingHourService.addWorkingHour(workingHour1);

		final WorkingHour workingHour2 = new WorkingHour();
		workingHour2.setUser(adminUser);
		workingHour2.setIssue(issue2);
		workingHour2.setStarting(createStartingDate("2017-11-25 08:00"));
		workingHour2.setDuration(createDuration(7, 40, 0));
		workingHourService.addWorkingHour(workingHour2);

		final WorkingHour workingHour3 = new WorkingHour();
		workingHour3.setUser(projectLeaderUser);
		workingHour3.setIssue(issue3);
		workingHour3.setStarting(createStartingDate("2017-11-23 11:00"));
		workingHour3.setDuration(createDuration(6, 50, 30));
		workingHourService.addWorkingHour(workingHour3);

		final WorkingHour workingHour4 = new WorkingHour();
		workingHour4.setUser(projectLeaderUser);
		workingHour4.setIssue(issue1);
		workingHour4.setStarting(createStartingDate("2017-11-24 07:00"));
		workingHour4.setDuration(createDuration(5, 0, 0));
		workingHourService.addWorkingHour(workingHour4);

		final WorkingHour workingHour5 = new WorkingHour();
		workingHour5.setUser(projectLeaderUser);
		workingHour5.setIssue(issue4);
		workingHour5.setStarting(createStartingDate("2017-11-25 07:30"));
		workingHour5.setDuration(createDuration(8, 15, 40));
		workingHourService.addWorkingHour(workingHour5);

		final WorkingHour deletableWorkingHour = new WorkingHour();
		deletableWorkingHour.setUser(deletableUser);
		deletableWorkingHour.setIssue(issue4);
		deletableWorkingHour.setStarting(createStartingDate("2017-11-20 07:30"));
		deletableWorkingHour.setDuration(createDuration(8, 0, 0));
		workingHourService.addWorkingHour(deletableWorkingHour);

		logger.log("working hours initialized successfully.");
		logger.log("finished initialization");
	}

	private static Date createStartingDate(final String dateText) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return dateFormat.parse(dateText);
		} catch (ParseException e) {
			logger.log("dateFormat cannot parse text " + dateText + ". Error: " + e.getMessage());
			return new Date();
		}
	}

	private long createDuration(final int hours, final int minutes, final int seconds) {
		return seconds + (60L * minutes) + (60L * 60L * hours);
	}

}

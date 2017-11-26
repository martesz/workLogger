package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.DebugLogger;
import entities.Issue;
import entities.Project;

@Stateless
public class ProjectIssueDao {
	public static final DebugLogger logger = new DebugLogger(ReportDao.class.getName());

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;

	public void insertProject(final Project project) {
		em.persist(project);
		logger.log("Added project to database: " + project);
	}

	public void clearProjects() {
		final int deletedCount = em.createQuery("DELETE FROM Project").executeUpdate();
		logger.log("Deleted projects count: " + deletedCount);
	}

	public void insertIssue(final Issue issue) {
		em.persist(issue);
		logger.log("Added issue to database: " + issue);
	}

	public void clearIssues() {
		final int deletedCount = em.createQuery("DELETE FROM Issue").executeUpdate();
		logger.log("Deleted issuess count: " + deletedCount);
	}
}

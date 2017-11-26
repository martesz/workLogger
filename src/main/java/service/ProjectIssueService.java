package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.ProjectIssueDao;
import entities.Issue;
import entities.Project;

@Stateless
public class ProjectIssueService {

	@EJB
	ProjectIssueDao projectIssueDao;

	public void addProject(final Project project) {
		projectIssueDao.insertProject(project);
	}
	
	public void clearProjects() {
		projectIssueDao.clearProjects();
	}

	public void addIssue(final Issue issue) {
		projectIssueDao.insertIssue(issue);
	}
	
	public void clearIssues() {
		projectIssueDao.clearIssues();
	}
}

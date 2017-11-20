package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.ProjectDao;
import entities.Project;

@Stateless
public class ReportService {

	@EJB
	ProjectDao projectDao;
	
	public Project getProjectReport(String projectName) {
		return projectDao.getProjectByName(projectName);
	}
}

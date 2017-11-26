package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.ReportDao;
import database.UserDao;
import entities.Report;
import entities.User;
import entities.WorkingHour;

@Stateless
public class ReportService {

	@EJB
	ReportDao reportDao;
	
	@EJB
	UserDao userDao;

	public List<Report> getReports(User owner) {
		return reportDao.getReports(owner);
	}

	public Report getReportById(long id) {
		return reportDao.getReportById(id);
	}

	public void addReport(Report report) {
		reportDao.insertReport(report);
	}

	public List<WorkingHour> getWorkingHoursForReport(Report report) {
		return reportDao.getWorkingHoursForReport(report);
	}

	public List<Report> getReports(String userId) {
		User user = userDao.getUserByGoogleId(userId);
		List<Report> reports = reportDao.getReports(user);
		return reports;
	}

}

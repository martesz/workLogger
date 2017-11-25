package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.ReportDao;
import entities.Report;
import entities.User;

@Stateless
public class ReportService {

	@EJB
	ReportDao reportDao;

	public List<Report> getReports(User owner) {
		return reportDao.getReports(owner);
	}

	public Report getReportById(long id) {
		return reportDao.getReportById(id);
	}

	public void addReport(Report report) {
		reportDao.insertReport(report);
	}

}

package database;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Report;
import entities.Report.ReportType;
import entities.User;
import entities.WorkingHour;

@Stateless
public class ReportDao {
	private static final String ALL_USERS = "allUser";

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;

	public void insertReport(Report report) {
		em.persist(report);
	}

	public void removeReport(Report report) {
		final Report databasereport = em.find(Report.class, report.getId());
		if (databasereport != null) {
			em.remove(databasereport);
		}
	}

	public List<Report> getReports(final User owner) {
		TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.owner =:owner", Report.class);
		query.setParameter("owner", owner);
		List<Report> resultList = query.getResultList();
		return resultList;
	}

	public Report getReportById(long id) {
		return em.find(Report.class, id);
	}

	public List<WorkingHour> getWorkingHoursForReport(Report report) {
		final Date startDate = report.getStartDate();
		final Date endDate = getEndDate(report.getStartDate(), report.getReportType());

		final TypedQuery<WorkingHour> query;
		if (report.getGoogleId().equals(ALL_USERS)) {
			query = em.createQuery("SELECT w FROM WorkingHour w WHERE w.starting BETWEEN :start AND :end",
					WorkingHour.class);
			query.setParameter("start", startDate);
			query.setParameter("end", endDate);
		} else {
			final User user = em.find(User.class, report.getGoogleId());
			query = em.createQuery(
					"SELECT w FROM WorkingHour w WHERE w.user=:user AND w.starting BETWEEN :start AND :end",
					WorkingHour.class);
			query.setParameter("user", user);
			query.setParameter("start", startDate);
			query.setParameter("end", endDate);
		}
		List<WorkingHour> resultList = query.getResultList();
		return resultList;
	}

	private Date getEndDate(Date startDate, ReportType reportType) {
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		final int length = reportType.getLengthDays();
		calendar.add(Calendar.HOUR_OF_DAY, length);
		return calendar.getTime();
	}

}

package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Report;
import entities.User;

@Stateless
public class ReportDao {
	
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

}

package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Issue;
import entities.User;
import entities.WorkingHour;

@Stateless
public class WorkingHourDao {

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;
	
	public void insertWorkingHour(WorkingHour workingHour) {
		em.persist(workingHour);
	}

	public void updateWorkingHour(WorkingHour workingHour) {
		em.merge(workingHour);
	}

	public void removeWorkingHour(WorkingHour workingHour) {
		final WorkingHour databaseWorkingHour = em.find(WorkingHour.class, workingHour.getId());
		if (databaseWorkingHour != null) {
			em.remove(databaseWorkingHour);
		}
	}

	public List<WorkingHour> getAllWorkingHours() {
		TypedQuery<WorkingHour> query = em.createQuery("SELECT w FROM WorkingHour w", WorkingHour.class);
		List<WorkingHour> resultList = query.getResultList();
		return resultList;
	}

	public List<WorkingHour> getWorkingHours(User user) {
		TypedQuery<WorkingHour> query = em.createQuery("SELECT w FROM WorkingHour w WHERE w.user =:user", WorkingHour.class);
		query.setParameter("user", user);
		List<WorkingHour> resultList = query.getResultList();
		return resultList;
	}

	public WorkingHour getWorkingHourById(long id) {
		return em.find(WorkingHour.class, id);
	}

	public List<Issue> getIssues() {
		TypedQuery<Issue> query = em.createQuery("SELECT i FROM Issue i", Issue.class);
		List<Issue> resultList = query.getResultList();
		return resultList;
	}

}

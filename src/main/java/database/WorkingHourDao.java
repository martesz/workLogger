package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		em.remove(workingHour);
	}
}

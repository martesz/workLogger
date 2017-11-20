package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Project;

@Stateless
public class ProjectDao {
	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;
	
	public Project getProjectByName(String projectName) {
		TypedQuery<Project> query = em.createQuery("SELECT p from Project p WHERE p.name =:name", Project.class);
		query.setParameter("name", projectName);
		return query.getSingleResult();
	}
}

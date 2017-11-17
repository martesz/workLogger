package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Person;

@Stateless
public class PersonDao {

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;
	
	public void insertPerson(Person person) {
		em.persist(person);
	}
	
	public List<Person> getPersons() {
		TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> resultList = query.getResultList();
		return resultList;
	}
}

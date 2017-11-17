package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.PersonDao;
import entities.Person;

@Stateless
public class PersonService {

	@EJB
	PersonDao personDao;
	
	public void addNewPerson(String name, String position) {
		Person person = Person.from(name, position);
		personDao.insertPerson(person);
	}
	
	public List<Person> getPersons() {
		return personDao.getPersons();
	}
}

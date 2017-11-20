package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;
import entities.WorkingHour;

@Stateless
public class UserDao {

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;

	public User getUserByGoogleId(String googleId) {
		User user = em.find(User.class, googleId);
		return user;
	}

	public void insertNewUser(User user) {
		em.persist(user);
	}

	public List<WorkingHour> getWorkingHours(String googleId) {
		User user = em.find(User.class, googleId);
		return user.getWorkingHours();
	}

}

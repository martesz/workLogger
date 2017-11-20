package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;
import entities.User.Level;
import entities.WorkingHour;

@Stateless
public class UserDao {

	@PersistenceContext(unitName = "workLoggerPu")
	EntityManager em;

	public User getUserByGoogleId(String googleId) {
		User user = em.find(User.class, googleId);
		return user;
	}

	public void insert(User user) {
			em.persist(user);
	}

	public List<WorkingHour> getWorkingHours(String googleId) {
		User user = em.find(User.class, googleId);
		return user.getWorkingHours();
	}

	public void updateLevel(String googleId, Level level) {
		User user = em.find(User.class, googleId);
		user.setLevel(level);
	}

}

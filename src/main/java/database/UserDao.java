package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.User;

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

	public void updateLevel(String googleId, String level) {
		User user = em.find(User.class, googleId);
		user.setLevelFromString(level);
	}

}

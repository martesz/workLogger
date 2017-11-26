package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public List<User> getUsers() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> resultList = query.getResultList();
		return resultList;
	}

	public void updateUser(final User user) {
		em.merge(user);
	}

	public void removeUser(final User user) {
		final User databaseUser = em.find(User.class, user.getGoogleId());
		if (databaseUser != null) {
			em.remove(databaseUser);
		}
	}

}

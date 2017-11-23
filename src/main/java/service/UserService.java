package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.UserDao;
import entities.User;
import entities.User.Level;

@Stateless
public class UserService {

	@EJB
	UserDao userDao;

	public User getUserByGoogleId(String googleId) {
		return userDao.getUserByGoogleId(googleId);
	}

	public User registerOrLoginUser(User user) {
		User existing = userDao.getUserByGoogleId(user.getGoogleId());
		if (existing == null) {
			user.setLevel(Level.EMPLOYEE);
			userDao.insert(user);
			return user;
		} else {
			return existing;
		}
	}

	public Level getLevelOfUser(String googleId) {
		User user = userDao.getUserByGoogleId(googleId);
		return user.getLevel();
	}

	public void updateLevel(String googleId, String level) {
		userDao.updateLevel(googleId, level);
	}
}

package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.UserDao;
import entities.DebugLogger;
import entities.User;
import entities.User.Level;

@Stateless
public class UserService {
	public static final DebugLogger logger = new DebugLogger(UserService.class.getName());

	@EJB
	UserDao userDao;

	public User getUserByGoogleId(String googleId) {
		return userDao.getUserByGoogleId(googleId);
	}

	public User registerOrLoginUser(User user) {
		User existing = loginUser(user.getGoogleId());
		if (existing == null) {
			user.setLevel(Level.EMPLOYEE);
			addUser(user);
			return user;
		} else {
			logger.log("User already existed, returning: " + existing);
			return existing;
		}
	}

	public User loginUser(final String googleId) {
		return userDao.getUserByGoogleId(googleId);
	}

	public void addUser(final User user) {
		logger.log("Adding user to database: " + user);
		userDao.insert(user);
	}

	public Level getLevelOfUser(String googleId) {
		User user = userDao.getUserByGoogleId(googleId);
		return user.getLevel();
	}

	public void updateLevel(String googleId, String level) {
		userDao.updateLevel(googleId, level);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

}

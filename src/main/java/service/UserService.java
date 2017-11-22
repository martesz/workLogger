package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.UserDao;
import entities.User;
import entities.User.Level;
import entities.WorkingHour;

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

	public List<WorkingHour> getWorkingHours(String googleId) {
		return userDao.getWorkingHours(googleId);
	}

	public Level getLevelOfUser(String googleId) {
		User user = userDao.getUserByGoogleId(googleId);
		return user.getLevel();
	}

	public void updateLevel(String googleId, String level) {
		userDao.updateLevel(googleId, level);
	}
}

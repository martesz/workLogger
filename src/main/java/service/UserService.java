package service;

import java.util.List;

import javax.ejb.EJB;

import database.UserDao;
import entities.User;
import entities.WorkingHour;

public class UserService {

	@EJB
	UserDao userDao;
	
	public User getUserByGoogleId(String googleId) {
		return userDao.getUserByGoogleId(googleId);
	}
	
	public void registerNewUser(User user) {
		userDao.insertNewUser(user);
	}
	
	public List<WorkingHour> getWorkingHours(String googleId) {
		return userDao.getWorkingHours(googleId);
	}
}

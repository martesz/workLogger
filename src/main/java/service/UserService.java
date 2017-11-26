package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.ReportDao;
import database.UserDao;
import database.WorkingHourDao;
import entities.DebugLogger;
import entities.Report;
import entities.User;
import entities.User.Level;
import entities.WorkingHour;

@Stateless
public class UserService {
	public static final DebugLogger logger = new DebugLogger(UserService.class.getName());

	@EJB
	UserDao userDao;
	@EJB
	WorkingHourDao hourDao;
	@EJB
	ReportDao reportDao;

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

	public void updateUser(final User user) {
		userDao.updateUser(user);
	}

	public void removeUser(final User user) {
		final User databaseUser = userDao.getUserByGoogleId(user.getGoogleId());
		if (databaseUser == null) {
			return;
		}
		final List<WorkingHour> workingHours = hourDao.getWorkingHours(databaseUser);
		if (workingHours != null) {
			for (final WorkingHour workingHour : workingHours) {
				hourDao.removeWorkingHour(workingHour);
			}
		}
		final List<Report> reports = reportDao.getReports(databaseUser);
		if (reports != null) {
			for (final Report report : reports) {
				reportDao.removeReport(report);
			}
		}
		userDao.removeUser(user);
	}

}

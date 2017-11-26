package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.UserDao;
import database.WorkingHourDao;
import entities.Issue;
import entities.User;
import entities.WorkingHour;

@Stateless
public class WorkingHourService {

	@EJB
	WorkingHourDao hourDao;
	
	@EJB
	UserDao userDao;
	
	public void addWorkingHour(WorkingHour workingHour) {
		hourDao.insertWorkingHour(workingHour);
	}

	public void updateWorkingHour(WorkingHour workingHour) {
		hourDao.updateWorkingHour(workingHour);
	}

	public void removeWorkingHour(WorkingHour workingHour) {
		hourDao.removeWorkingHour(workingHour);
	}

	public List<WorkingHour> getWorkingHours(User user) {
		return hourDao.getWorkingHours(user);
	}

	public WorkingHour getWorkingHourById(long id) {
		return hourDao.getWorkingHourById(id);
	}

	public List<Issue> getIssues() {
		return hourDao.getIssues();
	}

	public List<WorkingHour> getWorkingHourByUserId(String userId) {
		User user = userDao.getUserByGoogleId(userId);
		List<WorkingHour> workingHours = hourDao.getWorkingHours(user);
		return workingHours;
	}
}

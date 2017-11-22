package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.WorkingHourDao;
import entities.User;
import entities.WorkingHour;

@Stateless
public class WorkingHourService {

	@EJB
	WorkingHourDao hourDao;
	
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
}

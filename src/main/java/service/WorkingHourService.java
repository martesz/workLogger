package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import database.WorkingHourDao;
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
}

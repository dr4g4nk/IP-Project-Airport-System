package org.unibl.etf.ip_user.beans;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.unibl.etf.ip_user.dao.ScheduleDao;
import org.unibl.etf.ip_user.dto.Schedule;

public class ScheduleBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8509269715668031178L;

	public ScheduleBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Schedule> get5Arrivals(Date date){
		return ScheduleDao.get5Arrivals(date);
	}
	
	public List<Schedule> get5Departures(Date date){
		return ScheduleDao.get5Departures(date);
	}
	
	public List<Schedule> getArrivals(Date date){
		return ScheduleDao.getArrivals(date);
	}
	
	public List<Schedule> getDepartures(Date date){
		return ScheduleDao.getDepartures(date);
	}
}

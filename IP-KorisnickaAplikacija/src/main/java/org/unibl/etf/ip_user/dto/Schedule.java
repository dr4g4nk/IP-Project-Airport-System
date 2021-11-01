package org.unibl.etf.ip_user.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class Schedule implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3298279789317836518L;
	private long id;
	private Date day;
	private Time startTime, endTime;
	private Fligth fligth;
	private String status;
	
	transient private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	public Schedule() {
		// TODO Auto-generated constructor stub
	}

	public Schedule(long id, Date day, Time startTime, Time endTime, Fligth fligth, String status) {
		super();
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fligth = fligth;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public Date getDay() {
		return day;
	}

	public Time getStartTime() {
		return startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public Fligth getFligth() {
		return fligth;
	}

	public String getStatus() {
		return status;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public void setFligth(Fligth fligth) {
		this.fligth = fligth;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return startTime + ", "+dateFormat.format(day);
	}
	
	
}

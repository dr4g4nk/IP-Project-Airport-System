package org.unibl.etf.ip_user.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9186965135822189337L;
	private long id;
	private String status, reason;
	private Date date;
	private Fligth fligth;
	private int seat;
	private String description, specification;
	private User user;
	private Schedule schedule;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public Reservation(long id, String status, String reason, Date date, User user, Fligth fligth) {
		super();
		this.id = id;
		this.status = status;
		this.reason = reason;
		this.date = date;
		this.user = user;
		this.fligth = fligth;
	}
	
	

	public Reservation(long id, String status, String reason, Date date, User user, Fligth fligth, int seat) {
		super();
		this.id = id;
		this.status = status;
		this.reason = reason;
		this.date = date;
		this.user = user;
		this.fligth = fligth;
		this.seat = seat;
	}
	public Reservation(long id, String status, String reason, Date date, User user, Fligth fligth, int seat, Schedule schedule) {
		super();
		this.id = id;
		this.status = status;
		this.reason = reason;
		this.date = date;
		this.user = user;
		this.fligth = fligth;
		this.seat = seat;
		this.schedule = schedule;
	}
	
	public Reservation(long id, String status, String reason, Date date, User user, Fligth fligth,
			String description, String specification) {
		super();
		this.id = id;
		this.status = status;
		this.reason = reason;
		this.date = date;
		this.user = user;
		this.fligth = fligth;
		this.description = description;
		this.specification = specification;
	}
	
	public Reservation(long id, String status, String reason, Date date, User user, Fligth fligth,
			String description, String specification, Schedule schedule) {
		super();
		this.id = id;
		this.status = status;
		this.reason = reason;
		this.date = date;
		this.user = user;
		this.fligth = fligth;
		this.description = description;
		this.specification = specification;
		this.schedule = schedule;
	}

	public int getSeat() {
		return seat;
	}

	public String getDescription() {
		return description;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public String getDate() {
		return dateFormat.format(date);
	}

	public User getUser() {
		return user;
	}

	public Fligth getFligth() {
		return fligth;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setFligth(Fligth fligth) {
		this.fligth = fligth;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}

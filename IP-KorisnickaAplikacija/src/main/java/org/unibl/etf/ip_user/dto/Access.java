package org.unibl.etf.ip_user.dto;

import java.io.Serializable;
import java.sql.Date;

public class Access implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4930380663690043578L;
	private Date date;
	private int number;
	
	public Access() {
		// TODO Auto-generated constructor stub
	}

	public Access(Date date, int number) {
		super();
		this.date = date;
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public int getNumber() {
		return number;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}

package org.unibl.etf.ip_user.dto;

import java.io.Serializable;

public class Fligth implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8275033371556934311L;
	private long id;
	private String type;
	private Route route;
	
	public Fligth() {
		// TODO Auto-generated constructor stub
	}

	public Fligth(long id, String type, Route route) {
		super();
		this.id = id;
		this.type = type;
		this.route = route;
	}

	public long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Route getRoute() {
		return route;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
	@Override
	public String toString() {
		return route.getStart().toString()+" -> "+route.getEnd().toString();
	}

}

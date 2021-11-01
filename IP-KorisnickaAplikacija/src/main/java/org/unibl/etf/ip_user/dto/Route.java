package org.unibl.etf.ip_user.dto;

import java.io.Serializable;

public class Route implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550818491020016975L;
	private long id;
	private Location start;
	private Location end;
	
	public Route() {
		// TODO Auto-generated constructor stub
	}

	public Route(long id, Location start, Location end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}

	public long getId() {
		return id;
	}

	public Location getStart() {
		return start;
	}

	public Location getEnd() {
		return end;
	}

}

package org.unibl.etf.ip_user.dto;

import java.io.Serializable;

public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411081682435409515L;
	private long id;
	private String city, country;
	
	public Location() {
		// TODO Auto-generated constructor stub
	}

	public Location(long id, String city, String country) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	
	@Override
	public String toString() {
		return city+", "+country;
	}
}

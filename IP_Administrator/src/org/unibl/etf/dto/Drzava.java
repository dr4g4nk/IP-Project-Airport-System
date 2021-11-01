package org.unibl.etf.dto;

import java.io.Serializable;

public class Drzava implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7057825386410675041L;
	private String name;
	
	public Drzava() {
		// TODO Auto-generated constructor stub
	}

	public Drzava(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

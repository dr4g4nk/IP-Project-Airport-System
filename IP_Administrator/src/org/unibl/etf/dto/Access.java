package org.unibl.etf.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Access implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7813801660566565700L;
	private Date datum;
	private int brojPristupa;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.YYYY");
	
	public Access() {
		// TODO Auto-generated constructor stub
	}

	public Access(Date datum, int brojPristupa) {
		super();
		this.datum = datum;
		this.brojPristupa = brojPristupa;
	}

	public String getDatum() {
		return format.format(datum);
	}

	public int getBrojPristupa() {
		return brojPristupa;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setBrojPristupa(int brojPristupa) {
		this.brojPristupa = brojPristupa;
	}

}

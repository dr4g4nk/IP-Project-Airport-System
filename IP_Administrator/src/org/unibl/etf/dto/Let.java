package org.unibl.etf.dto;

public class Let {

	private long id;
	private String tip;
	private Putanja putanja;
	
	public Let() {
	}

	public Let(long id, String tip, Putanja putanja) {
		super();
		this.id = id;
		this.tip = tip;
		this.putanja = putanja;
	}

	public long getId() {
		return id;
	}

	public String getTip() {
		return tip;
	}

	public Putanja getPutanja() {
		return putanja;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	public void setPutanja(Putanja putanja) {
		this.putanja = putanja;
	}
	
	
}

package org.unibl.etf.dto;

public class Putanja {

	private long id;
	private Lokacija polaznaLokacija;
	private Lokacija odredisnaLokacija;

	public Putanja() {
		// TODO Auto-generated constructor stub
	}

	public Putanja(long id, Lokacija polaznaLokacija, Lokacija odredisnaLokacija) {
		super();
		this.id = id;
		this.polaznaLokacija = polaznaLokacija;
		this.odredisnaLokacija = odredisnaLokacija;
	}

	public long getId() {
		return id;
	}

	public Lokacija getPolaznaLokacija() {
		return polaznaLokacija;
	}

	public Lokacija getOdredisnaLokacija() {
		return odredisnaLokacija;
	}

	public void setPolaznaLokacija(Lokacija polaznaLokacija) {
		this.polaznaLokacija = polaznaLokacija;
	}

	public void setOdredisnaLokacija(Lokacija odredisnaLokacija) {
		this.odredisnaLokacija = odredisnaLokacija;
	}

	@Override
	public String toString() {
		return "Putanja [polaznaLokacija=" + polaznaLokacija + ", odredisnaLokacija=" + odredisnaLokacija + "]";
	}
	
	

}

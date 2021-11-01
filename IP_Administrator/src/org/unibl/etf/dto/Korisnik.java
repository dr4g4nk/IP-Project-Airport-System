package org.unibl.etf.dto;

public class Korisnik extends Zaposleni {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4411131681683280838L;
	private String email, adresa;
	private String vrstaNaloga;
	private String drzava;
	
	public Korisnik() {
	}

	public Korisnik(String email, String korisnickoIme, String lozinka, String ime, String prezime, String adresa,
		 String vrstaNaloga, String drzava) {
		super(ime, prezime, korisnickoIme, lozinka);
		this.email = email;
		this.adresa = adresa;
		this.vrstaNaloga = vrstaNaloga;
		this.drzava = drzava;
	}
	
	public Korisnik(Zaposleni zaposleni) {
		super(zaposleni);
	}
	
	public Korisnik(Korisnik korisnik) {
		super(korisnik);
		this.email = korisnik.email;
		this.adresa = korisnik.adresa;
		this.vrstaNaloga = korisnik.vrstaNaloga;
		this.drzava = korisnik.drzava;
	}

	public String getEmail() {
		return email;
	}

	public String getAdresa() {
		return adresa;
	}

	public String getVrstaNaloga() {
		return vrstaNaloga;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public void setVrstaNaloga(String vrstaNaloga) {
		this.vrstaNaloga = vrstaNaloga;
	}
	
	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((korisnickoIme == null) ? 0 : korisnickoIme.hashCode());
		result = prime * result + ((lozinka == null) ? 0 : lozinka.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		result = prime * result + ((vrstaNaloga == null) ? 0 : vrstaNaloga.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (korisnickoIme == null) {
			if (other.korisnickoIme != null)
				return false;
		} else if (!korisnickoIme.equals(other.korisnickoIme))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Korisnik [email=" + email + ", ime=" + ime + ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme
				+ ", lozinka=" + lozinka + ", adresa=" + adresa + ", vrstaNaloga=" + vrstaNaloga
				+ "]";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	
	
}

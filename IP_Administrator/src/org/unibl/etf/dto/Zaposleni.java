package org.unibl.etf.dto;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Zaposleni implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1021332878377465643L;
	protected String ime, prezime, korisnickoIme, lozinka;
	private boolean enabled = true;
	
	private boolean loggedIn;
	
	public Zaposleni() {
		// TODO Auto-generated constructor stub
	}

	public Zaposleni(String ime, String prezime, String korisnickoIme, String lozinka) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}

	public Zaposleni(String ime, String prezime, String korisnickoIme, String lozinka, boolean enabled) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.enabled = enabled;
	}

	public Zaposleni(Zaposleni zaposleni) {
		this.ime = zaposleni.ime;
		this.prezime = zaposleni.prezime;
		this.korisnickoIme = zaposleni.korisnickoIme;
		this.lozinka = zaposleni.lozinka;
		this.enabled = zaposleni.enabled;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void generateHash() {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(lozinka.getBytes(StandardCharsets.UTF_8));
			String encoded = Base64.getEncoder().encodeToString(hash);
			this.lozinka = encoded;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
		}
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		return "Zaposleni [ime=" + ime + ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme + "]";
	}

	
}

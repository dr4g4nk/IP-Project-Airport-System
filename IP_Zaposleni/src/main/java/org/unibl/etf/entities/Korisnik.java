package org.unibl.etf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="korisnik")
public class Korisnik {

	@Id
	private String email;
	
	@NotNull
	@Column(name="ime")
	private String ime;
	
	@NotNull
	@Column(name="prezime")
	private String prezime;
	
	@NotNull
	@Column(name="korisnicko_ime")
	private String korisnickoIme;

	@NotNull
	@Column(name="adresa")
	private String adresa;
	
	@NotNull
	@Column(name="vrsta_naloga")
	private String vrstaNaloga;
	
	@NotNull
	@Column(name="drzava")
	private String drzava;
	
	public Korisnik() {
		// TODO Auto-generated constructor stub
	}

	public Korisnik(String email, @NotNull String ime, @NotNull String prezime, @NotNull String korisnickoIme,
			@NotNull String adresa, @NotNull String vrstaNaloga, @NotNull String drzava) {
		super();
		this.email = email;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.adresa = adresa;
		this.vrstaNaloga = vrstaNaloga;
		this.drzava = drzava;
	}

	public String getEmail() {
		return email;
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

	public String getAdresa() {
		return adresa;
	}

	public String getVrstaNaloga() {
		return vrstaNaloga;
	}

	public String getDrzava() {
		return drzava;
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

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public void setVrstaNaloga(String vrstaNaloga) {
		this.vrstaNaloga = vrstaNaloga;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

}

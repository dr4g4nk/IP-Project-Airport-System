package org.unibl.etf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.unibl.etf.encoder.MyPasswordEncoder;

@Entity
@Table(name = "zaposleni")
public class Zaposleni {

	@Id
	@Column(name = "korisnicko_ime")
	private String korisnickoIme;
	
	@NotBlank
	@Column(name = "lozinka")
	private String lozinka;
	
	@NotNull
	@Column(name = "enabled")
	private boolean enabled;
	
	@NotBlank
	@Column(name = "role")
	private String role;
	
	private boolean loggedIn = false;
	
	public Zaposleni() {
		// TODO Auto-generated constructor stub
	}

	public Zaposleni(String korisnickoIme, @NotBlank String lozinka, @NotNull boolean enabled, @NotBlank String role) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.enabled = enabled;
		this.role = role;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getRole() {
		return role;
	}
	
	public void generatePassword() {
		lozinka = new MyPasswordEncoder().encode(lozinka);
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
}

package org.unibl.etf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "poruka")
public class Poruka {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotNull
	@Size(max = 45)
	@Column(name = "status")
	private String status;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "email")
	private String email;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "naslov")
	private String naslov;
	
	@NotNull
	@Column(name = "datum")
	private String datum;
	
	@NotNull
	@Column(name = "sadrzaj")
	private String sadrzaj;
	
	
	public Poruka() {
		
	}


	public Poruka(long id, @NotNull @Size(max = 45) String status, @NotNull @Size(max = 100) String email,
			@NotNull @Size(max = 100) String naslov, @NotNull String datum, @NotNull String sadrzaj) {
		super();
		this.id = id;
		this.status = status;
		this.email = email;
		this.naslov = naslov;
		this.datum = datum;
		this.sadrzaj = sadrzaj;
	}


	public long getId() {
		return id;
	}


	public String getStatus() {
		return status;
	}


	public String getEmail() {
		return email;
	}


	public String getNaslov() {
		return naslov;
	}


	public String getDatum() {
		return datum;
	}


	public String getSadrzaj() {
		return sadrzaj;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

}

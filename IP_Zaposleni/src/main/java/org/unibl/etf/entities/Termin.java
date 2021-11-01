package org.unibl.etf.entities;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "termin")
public class Termin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "dan")
	private Date dan;
	
	@NotNull
	@Column(name = "polazak")
	private Time polazak;	
	
	@NotNull
	@Column(name = "dolazak")
	private Time dolazak;	
	
	@ManyToOne
	private Let l;
	
	public Termin() {
		
	}

	public Termin(long id, @NotNull Date dan, @NotNull Time polazak, @NotNull Time dolazak, Let l) {
		super();
		this.id = id;
		this.dan = dan;
		this.polazak = polazak;
		this.dolazak = dolazak;
		this.l = l;
	}

	public long getId() {
		return id;
	}

	public Date getDan() {
		return dan;
	}

	public Time getPolazak() {
		return polazak;
	}

	public Time getDolazak() {
		return dolazak;
	}

	public Let getL() {
		return l;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDan(Date dan) {
		this.dan = dan;
	}

	public void setPolazak(Time polazak) {
		this.polazak = polazak;
	}

	public void setDolazak(Time dolazak) {
		this.dolazak = dolazak;
	}

	public void setL(Let l) {
		this.l = l;
	}

}

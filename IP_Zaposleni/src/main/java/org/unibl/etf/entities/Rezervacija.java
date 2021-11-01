package org.unibl.etf.entities;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 45)
	@Column(name = "status")
	private String status;
	
	@NotNull
	@Column(name = "datum_kreiranja")
	private Date datumKreiranja;
	
	@Column(name = "razlog")
	private String razlog;
	
	@ManyToOne
	@JoinColumn(name = "let_id", nullable = false)
	private Let l;
	
	@Cascade(CascadeType.DELETE)
	@ManyToOne
	@JoinColumn(name="email", nullable = false)
	private Korisnik korisnik;
	
	@Cascade(CascadeType.DELETE)
	@ManyToOne
	@JoinColumn(name="termin_id", nullable = false)
	private Termin termin;
	
	public Rezervacija() {
		// TODO Auto-generated constructor stub
	}

	public Rezervacija(long id, @NotNull @Size(max = 45) String status, @NotNull Date datumKreiranja, String razlog,
			Let l, Korisnik korisnik, Termin termin) {
		super();
		this.id = id;
		this.status = status;
		this.datumKreiranja = datumKreiranja;
		this.razlog = razlog;
		this.l = l;
		this.korisnik = korisnik;
		this.termin = termin;
	}

	public long getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public String getRazlog() {
		return razlog;
	}

	public Let getL() {
		return l;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}

	public void setL(Let l) {
		this.l = l;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}
}

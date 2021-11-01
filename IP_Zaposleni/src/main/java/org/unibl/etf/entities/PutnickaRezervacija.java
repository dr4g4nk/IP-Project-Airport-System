package org.unibl.etf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "putnicka_rezervacija")
public class PutnickaRezervacija {

	@Id
	private Long id;
	
	@Cascade(CascadeType.DELETE)
	@OneToOne
	@JoinColumn(name="id")
	private Rezervacija rezervacija;
	
	public PutnickaRezervacija() {
		// TODO Auto-generated constructor stub
	}

	public PutnickaRezervacija(Long id, Rezervacija rezervacija) {
		super();
		this.id = id;
		this.rezervacija = rezervacija;
	}

	public Long getId() {
		return id;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

}

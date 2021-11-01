package org.unibl.etf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "teretna_rezervacija")
public class TeretnaRezervacija {

	@Id
	private long id;
	
	@Cascade(CascadeType.DELETE)
	@OneToOne
	@JoinColumn(name="id")
	private Rezervacija rezervacija;
	
	public TeretnaRezervacija() {
		// TODO Auto-generated constructor stub
	}

	public TeretnaRezervacija(long id, Rezervacija rezervacija) {
		super();
		this.id = id;
		this.rezervacija = rezervacija;
	}

	public long getId() {
		return id;
	}

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

}

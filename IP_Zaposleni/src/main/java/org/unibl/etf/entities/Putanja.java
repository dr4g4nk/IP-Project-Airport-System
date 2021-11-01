package org.unibl.etf.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name="putanja")
public class Putanja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="polazna_lokacija_id")
	@NotNull
	private Lokacija polaznaLokacija;
	
	@OneToOne
	@JoinColumn(name="odredisna_lokacija_id")
	@NotNull
	private Lokacija odredisnaLokacija;
	
	public Putanja() {
		// TODO Auto-generated constructor stub
	}

	public Putanja(Long id, Lokacija polaznaLokacija, Lokacija odredisnaLokacija) {
		super();
		this.id = id;
		this.polaznaLokacija = polaznaLokacija;
		this.odredisnaLokacija = odredisnaLokacija;
	}

	public Long getId() {
		return id;
	}

	public Lokacija getPolaznaLokacija() {
		return polaznaLokacija;
	}

	public Lokacija getOdredisnaLokacija() {
		return odredisnaLokacija;
	}

}

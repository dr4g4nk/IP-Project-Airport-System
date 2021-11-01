package org.unibl.etf.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="let")
public class Let {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 45)
    @Column(name = "tip")
	private String tip;
	
	@OneToOne
	@JoinColumn(name = "putanja_id", nullable = false)
	private Putanja putanja;
	
	@Transient
	private List<Termin> termini;
	
	public Let() {
		
	}

	public Let(long id, @NotNull @Size(max = 45) String tip, Putanja putanja,
			List<Termin> termini) {
		super();
		this.id = id;
		this.tip = tip;
		this.putanja = putanja;
		this.termini = termini;
	}

	public long getId() {
		return id;
	}

	public String getTip() {
		return tip;
	}

	public Putanja getPutanja() {
		return putanja;
	}

	public List<Termin> getTermini() {
		return termini;
	}
	
	
}

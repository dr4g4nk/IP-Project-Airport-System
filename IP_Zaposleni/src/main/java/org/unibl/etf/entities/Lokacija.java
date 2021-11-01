package org.unibl.etf.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "lokacija")
public class Lokacija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Column(name = "grad")
	private String grad;
	
	@NotBlank
	@Column(name = "drzava")
	private String drzava;
	
	public Lokacija() {
		// TODO Auto-generated constructor stub
	}

	public Lokacija(long id, @NotBlank String grad, @NotBlank String drzava) {
		super();
		this.id = id;
		this.grad = grad;
		this.drzava = drzava;
	}

	public long getId() {
		return id;
	}

	public String getGrad() {
		return grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

}

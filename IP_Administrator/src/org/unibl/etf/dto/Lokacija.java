package org.unibl.etf.dto;

import java.io.Serializable;

public class Lokacija implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4036319816151221769L;
	private long id;
	private String grad, drzava;
	
	public Lokacija() {
		// TODO Auto-generated constructor stub
	}

	public Lokacija(long id, String grad, String drzava) {
		super();
		this.id = id;
		this.grad = grad;
		this.drzava = drzava;
	}
	
	public Lokacija(Lokacija lokacija) {
		this(lokacija.id, lokacija.grad, lokacija.drzava);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drzava == null) ? 0 : drzava.hashCode());
		result = prime * result + ((grad == null) ? 0 : grad.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lokacija other = (Lokacija) obj;
		if (drzava == null) {
			if (other.drzava != null)
				return false;
		} else if (!drzava.equals(other.drzava))
			return false;
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
		if (id != other.id)
			return false;
		return true;
	}



}

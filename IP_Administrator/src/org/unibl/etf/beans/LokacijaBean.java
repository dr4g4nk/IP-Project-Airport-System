package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.unibl.etf.dao.LokacijaDao;
import org.unibl.etf.dto.Drzava;
import org.unibl.etf.dto.Lokacija;
import org.unibl.etf.rest.client.RestClient;


@ManagedBean(name = "lokacijaBean")
@ViewScoped
public class LokacijaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7210577357781699973L;
	private static List<Drzava> drzave;
	private List<Lokacija> lokacije = LokacijaDao.getLokacije();
	private List<SelectItem> items = new ArrayList<SelectItem>();
	private Lokacija lokacija = new Lokacija();
	private String errorMessage;
	private boolean update;
	private long putanjaId = 0;

	public LokacijaBean() {
		drzave = RestClient.getDrzave();
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (map.containsKey("putanja-id")) {
			putanjaId = Long.parseLong(map.get("putanja-id"));
		}
		drzave.forEach(d ->{
			items.add(new SelectItem(d.getName()));
		});
	}
	
	
	public List<SelectItem> getItems() {
		return items;
	}
	
	public List<Drzava> getDrzave() {
		return drzave;
	}

	public Lokacija getLokacija() {
		return lokacija;
	}
	
	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Lokacija> getLokacije() {
		return lokacije;
	}

	public boolean isUpdate() {
		return update;
	}


	public String save() {
		if (lokacija.getDrzava().isBlank() || lokacija.getGrad().isBlank()) {
			errorMessage = "Uneseni podaci nisu ispravni.";
			return null;
		} else {
			boolean b = LokacijaDao.insertLokacija(lokacija);
			if (b)
				return "putanja.xhtml?faces-redirect=true" + (putanjaId > 0 ? "&id=" + putanjaId : "");
			else {
				errorMessage = "Podaci nisu sacuvani.";
				System.out.println("greska");
				return null;
			}
		}
	}

}

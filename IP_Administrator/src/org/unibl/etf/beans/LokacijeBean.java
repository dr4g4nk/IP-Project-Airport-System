package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.unibl.etf.dao.LokacijaDao;
import org.unibl.etf.dto.Lokacija;

@ManagedBean(name = "lokacijeBean")
@ViewScoped
public class LokacijeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5563732782992501286L;
	private List<Lokacija> lokacije;
	private Lokacija lokacija = new Lokacija();
	private String msg;

	public LokacijeBean() {
		lokacije = LokacijaDao.getLokacije();
	}

	
	public Lokacija getLokacija() {
		return lokacija;
	}


	public List<Lokacija> getLokacije() {
		return lokacije;
	}


	public void setLokacije(List<Lokacija> lokacije) {
		this.lokacije = lokacije;
	}


	public void setLokacija(Lokacija lokacija) {
		this.lokacija = lokacija;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String delete() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (map.containsKey("id")) {
			int id = Integer.parseInt(map.get("id"));
			if(lokacije.stream().anyMatch(l -> l.getId() == id)) {
			Lokacija tmp = lokacije.stream().filter(l -> l.getId() == id).findFirst().get();
			
			lokacije.remove(tmp);
			boolean b = LokacijaDao.deleteLokacija(tmp);

			if (b)
				System.out.println("Brisanje uspjesno.");
			else
				msg = "Brisanje nije uspjelo.";
			}
		} else
			msg = "Brisanje nije uspjelo.";

		return null;
	}

}

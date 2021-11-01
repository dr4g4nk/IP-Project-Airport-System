package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.unibl.etf.dao.ZaposleniDao;
import org.unibl.etf.dto.Zaposleni;


@ManagedBean(name="zaposleniBean")
@ViewScoped
public class ZaposleniBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5653158538205909624L;
	private List<Zaposleni> zaposleni;
	private Zaposleni zaposlenik = new Zaposleni();

	public ZaposleniBean() {
		zaposleni = ZaposleniDao.getZaposlene();
	}

	public List<Zaposleni> getZaposleni() {
		return zaposleni;
	}

	public Zaposleni getZaposlenik() {
		return zaposlenik;
	}

	public void setZaposleni(List<Zaposleni> zaposleni) {
		this.zaposleni = zaposleni;
	}

	public void setZaposlenik(Zaposleni zaposlenik) {
		this.zaposlenik = zaposlenik;
	}
	
	public String delete() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(map.containsKey("korisnickoIme")) {
			String korisnickoIme=map.get("korisnickoIme");
			Zaposleni zap = zaposleni.stream().filter(z -> z.getKorisnickoIme().equals(korisnickoIme)).findFirst().get();
			boolean b = ZaposleniDao.deleteZaposleni(zap);
			
			if (b) {
				System.out.println("Zaposleni je obrisan");
				zaposleni.remove(zap);
			}
			else
				System.out.println("Brisanje nije uspjelo");
		}
		return null;
	}

}

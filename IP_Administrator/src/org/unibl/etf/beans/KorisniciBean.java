package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.unibl.etf.dao.KorisnikDao;
import org.unibl.etf.dto.Korisnik;

@ManagedBean(name = "korisniciBean")
@ViewScoped
public class KorisniciBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1267167177476341025L;
	private List<Korisnik> korisnici;
	private Korisnik korisnik = new Korisnik();

	public KorisniciBean() {
		korisnici = KorisnikDao.getKorisnici();
	}

	public List<Korisnik> getKorisnici() {
		return korisnici;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public String delete() {

		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		if (map.containsKey("email")) {
			String email = map.get("email");
			Korisnik k = korisnici.stream().filter(k1 -> k1.getEmail().equals(email)).findFirst().get();
			if (k != null) {
				boolean b = KorisnikDao.deleteKorisnik(k);
				if (b) {
					System.out.println("Korsisnik obrisan");
					korisnici.remove(k);
				} else
					System.out.println("brisanje nije uspjelo");
			}
		}

		return null;
	}

	public String edit(Korisnik k) {
		return "korisnik.xhtml?id=" + k.getEmail();
	}

}

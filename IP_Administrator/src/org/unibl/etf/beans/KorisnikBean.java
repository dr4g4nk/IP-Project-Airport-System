package org.unibl.etf.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.unibl.etf.dao.KorisnikDao;
import org.unibl.etf.dao.ZaposleniDao;
import org.unibl.etf.dto.Drzava;
import org.unibl.etf.dto.Korisnik;
import org.unibl.etf.dto.Zaposleni;
import org.unibl.etf.rest.client.RestClient;

@ManagedBean(name = "korisnikBean")
@ViewScoped
public class KorisnikBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6591152196313556332L;
	private Korisnik oldKorisnik = null, newKorisnik;
	private static List<Drzava> drzave;
	private List<String> vrsteNaloga = new ArrayList<String>();
	private String errorMessage;
	private Pattern pattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9\\-\\_]+\\@.+\\.[a-zA-Z]+");
	private boolean isKorisnik;
	
	static {
		new Thread() {
			@Override
			public void run() {
				drzave = RestClient.getDrzave();
			}
		}.start();
	}

	public KorisnikBean() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		if (map.containsKey("korisnik")) {
			isKorisnik = true;
		}

		if (map.containsKey("id")) {
			String id = map.get("id");
			
			if (isKorisnik) {
				Korisnik tmp = KorisnikDao.getKorisnikById(id);
				if(tmp != null)
					oldKorisnik = tmp;
			}
			else {
				Zaposleni tmp = ZaposleniDao.getZaposleniById(id);
				if(tmp!= null)
					oldKorisnik = new Korisnik(tmp);
			}
			if(oldKorisnik != null)
				newKorisnik = new Korisnik(oldKorisnik);
			else {
				oldKorisnik = newKorisnik = new Korisnik();
			}

		} else
			newKorisnik = new Korisnik();

		if (isKorisnik) {
			vrsteNaloga.add("Putnicki");
			vrsteNaloga.add("Teretni");
		}
	}

	public Korisnik getOldKorisnik() {
		return oldKorisnik;
	}

	public Korisnik getNewKorisnik() {
		return newKorisnik;
	}

	public void setOldKorisnik(Korisnik oldKorisnik) {
		this.oldKorisnik = oldKorisnik;
	}

	public void setNewKorisnik(Korisnik newKorisnik) {
		this.newKorisnik = newKorisnik;
	}

	public List<Drzava> getDrzave() {
		return drzave;
	}


	public List<String> getVrsteNaloga() {
		return vrsteNaloga;
	}

	public void setVrsteNaloga(List<String> vrsteNaloga) {
		this.vrsteNaloga = vrsteNaloga;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String insert() {
		if (newKorisnik.getIme() == null || newKorisnik.getIme().isBlank() || newKorisnik.getPrezime() == null
				|| newKorisnik.getPrezime().isBlank() || newKorisnik.getKorisnickoIme() == null
				|| newKorisnik.getKorisnickoIme().isBlank()
				|| (isKorisnik && (newKorisnik.getEmail() == null || !pattern.matcher(newKorisnik.getEmail()).matches()
						|| newKorisnik.getAdresa() == null || newKorisnik.getAdresa().isBlank()
						|| newKorisnik.getVrstaNaloga() == null || newKorisnik.getVrstaNaloga().isBlank()))) {

			errorMessage = "Uneseni podaci nisu ispravni.";
			return null;
		}
		boolean b;
		if (isKorisnik)
			b = KorisnikDao.insertKorisnik(newKorisnik);
		else
			b = ZaposleniDao.insertZaposleni(newKorisnik);
		if (b) {
			System.out.println("Insert");
		} else
			System.out.println("Greska");
		if (b && isKorisnik)
			return "/korisnici.xhtml?faces-redirect=true";
		else if (b)
			return "/zaposleni.xhtml?faces-redirect=true";
		return null;
	}

	public String update() {
		boolean b;
		if ((newKorisnik.getIme() == null || newKorisnik.getIme().isBlank() || newKorisnik.getPrezime() == null
				|| newKorisnik.getPrezime().isBlank() || newKorisnik.getKorisnickoIme() == null
				|| newKorisnik.getKorisnickoIme().isBlank())
				|| (isKorisnik && (newKorisnik.getEmail() == null || !pattern.matcher(newKorisnik.getEmail()).matches()
						|| newKorisnik.getAdresa() == null || newKorisnik.getAdresa().isBlank()
						|| newKorisnik.getVrstaNaloga() == null || newKorisnik.getVrstaNaloga().isBlank()))) {

			errorMessage = "Uneseni podaci nisu ispravni.";
			return null;
		} else {

			if (isKorisnik)
				b = KorisnikDao.updateKorisnik(newKorisnik, oldKorisnik);
			else {
				b = ZaposleniDao.updateZaposleni(newKorisnik, oldKorisnik);
			}
			if (b)
				System.out.println("Update");
			else
				System.out.println("Greska");

			if (isKorisnik)
				return "/korisnici.xhtml?faces-redirect=true";
			return "/zaposleni.xhtml?faces-redirect=true";
		}
	}

	public String updateLozinka() {
		if (newKorisnik.getLozinka() == null || newKorisnik.getLozinka().isBlank()) {

			errorMessage = "Uneseni podaci nisu ispravni.";
			return null;
		} else {
			newKorisnik.generateHash();
			boolean b;
			if (isKorisnik)
				b = KorisnikDao.updateLozinka(newKorisnik);
			else
				b = ZaposleniDao.updateLozinka(newKorisnik);
			if (b && isKorisnik)
				return "/korisnici.xhtml?faces-redirect=true";
			else if (b)
				return "/zaposleni.xhtml?faces-redirect=true&zaposleni";
			else {
				errorMessage = "Greska prilikom promjene lozinka.";
				return null;
			}
		}
	}

}

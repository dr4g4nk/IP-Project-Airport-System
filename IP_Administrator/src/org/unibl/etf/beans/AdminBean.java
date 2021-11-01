package org.unibl.etf.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.unibl.etf.dao.ZaposleniDao;
import org.unibl.etf.dto.Zaposleni;

@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6591152196313556332L;

	private Zaposleni admin = new Zaposleni();

	public AdminBean() {
	}

	public Zaposleni getAdmin() {
		return admin;
	}

	public void setAdmin(Zaposleni admin) {
		this.admin = admin;
	}

	public String login() {
		admin.generateHash();
		Zaposleni tmp = ZaposleniDao.getAdministrator(admin.getKorisnickoIme());
		if (tmp != null && tmp.getLozinka().equals(admin.getLozinka())) {
			admin = tmp;
			admin.setLoggedIn(true);
			System.out.println("Prijava uspjesna " + admin.toString());
			return "/home.xhtml";
		} else {
			admin.setKorisnickoIme("");
			System.out.println("Neuspjesna prijava");
		}
		return null;
	}

	public String korisnici() {
		if (admin.isLoggedIn()) {
			return "/korisnici.xhtml";
		}
		return null;
	}

	public String logout() {
		admin.setLoggedIn(false);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}
	
}

package org.unibl.etf.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.unibl.etf.dao.LokacijaDao;
import org.unibl.etf.dao.PutanjaDao;
import org.unibl.etf.dto.Lokacija;
import org.unibl.etf.dto.Putanja;

@ManagedBean(name = "putanjaBean")
@ViewScoped
public class PutanjaBean {

	private Putanja putanja = new Putanja();
	private List<Putanja> putanje = PutanjaDao.getPutanje();
	private List<Lokacija> lokacije = LokacijaDao.getLokacije();
	private List<String> pDrzave;
	private List<String> pGradovi;
	private List<String> oDrzave;
	private List<String> oGradovi;
	private String polaznaDrzava, polazniGrad;
	private String odredisnaDrzava, odredisniGrad;
	private String msg;
	private boolean update;

	public PutanjaBean() {
		String tmp[] = lokacije.stream().map(l -> l.getDrzava()).distinct().toArray(String[]::new);
		pDrzave = Arrays.asList(tmp);
		oDrzave = Arrays.asList(tmp);
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (map.containsKey("id")) {
			long id = Integer.parseInt(map.get("id"));
			putanja = PutanjaDao.getPutanjaById(id);
			if (putanja == null)
				putanja = new Putanja();
			else {
				setPolaznaDrzava(putanja.getPolaznaLokacija().getDrzava());
				polazniGrad = putanja.getPolaznaLokacija().getGrad();
				setOdredisnaDrzava(putanja.getOdredisnaLokacija().getDrzava());
				odredisniGrad = putanja.getOdredisnaLokacija().getGrad();
				update = true;
			}
		} else {
			polaznaDrzava = lokacije.isEmpty() ? null : lokacije.get(0).getDrzava();
			tmp = lokacije.stream().filter(l -> l.getDrzava().equals(polaznaDrzava)).map(l -> l.getGrad())
					.toArray(String[]::new);
			pGradovi = Arrays.asList(tmp);
			polazniGrad = pGradovi.size() > 0 ? pGradovi.get(0) : null;
			odredisnaDrzava = lokacije.isEmpty() ? null : lokacije.get(0).getDrzava();
			oGradovi = Arrays.asList(tmp);
			odredisniGrad = oGradovi.size() > 0 ? oGradovi.get(0) : null;
		}
	}

	public List<String> getpDrzave() {
		return pDrzave;
	}

	public List<String> getpGradovi() {
		return pGradovi;
	}

	public List<String> getoDrzave() {
		return oDrzave;
	}

	public List<String> getoGradovi() {
		return oGradovi;
	}

	public String getPolaznaDrzava() {
		return polaznaDrzava;
	}

	public String getPolazniGrad() {
		return polazniGrad;
	}

	public String getOdredisnaDrzava() {
		return odredisnaDrzava;
	}

	public String getOdredisniGrad() {
		return odredisniGrad;
	}

	public void setOdredisnaDrzava(String odredisnaDrzava) {
		this.odredisnaDrzava = odredisnaDrzava;
		String tmp[] = lokacije.stream().filter(l -> l.getDrzava().equals(odredisnaDrzava)).map(l -> l.getGrad())
				.toArray(String[]::new);
		oGradovi = Arrays.asList(tmp);
		odredisniGrad = oGradovi.size() > 0 ? oGradovi.get(0) : null;
	}

	public void setPolaznaDrzava(String polaznaDrzava) {
		this.polaznaDrzava = polaznaDrzava;
		String tmp[] = lokacije.stream().filter(l -> l.getDrzava().equals(polaznaDrzava)).map(l -> l.getGrad())
				.toArray(String[]::new);
		pGradovi = Arrays.asList(tmp);
		polazniGrad = pGradovi.size() > 0 ? pGradovi.get(0) : null;
	}

	public void setPolazniGrad(String polazniGrad) {
		this.polazniGrad = polazniGrad;
	}

	public void setOdredisniGrad(String odredisniGrad) {
		System.out.println("Set odredisni Grad");
		this.odredisniGrad = odredisniGrad;
	}

	public List<Lokacija> getLokacije() {
		return lokacije;
	}

	public String getMsg() {
		return msg;
	}

	public Putanja getPutanja() {
		return putanja;
	}

	public List<Putanja> getPutanje() {
		return putanje;
	}

	public void setPutanja(Putanja putanja) {
		this.putanja = putanja;
	}

	public void setPutanje(List<Putanja> putanje) {
		this.putanje = putanje;
	}

	public boolean isUpdate() {
		return update;
	}

	public String delete() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if (map.containsKey("id")) {
			long id = Long.parseLong(map.get("id"));
			if (putanje.stream().anyMatch(p -> p.getId() == id)) {
				Putanja tmp = putanje.stream().filter(p -> p.getId() == id).findFirst().get();

				putanje.remove(tmp);
				PutanjaDao.delete(tmp);
			}
		}
		return null;
	}

//	public String save() {
//		Lokacija polazna = lokacije.stream()
//				.filter(l -> l.getDrzava().equals(polaznaDrzava) && l.getGrad().equals(polazniGrad)).findFirst().get();
//		Lokacija odredisna = lokacije.stream()
//				.filter(l -> l.getDrzava().equals(odredisnaDrzava) && l.getGrad().equals(odredisniGrad)).findFirst()
//				.get();
//
//		putanja.setPolaznaLokacija(polazna);
//		putanja.setOdredisnaLokacija(odredisna);
//
//		if (putanja.getPolaznaLokacija() != null && putanja.getOdredisnaLokacija() != null) {
//			if (putanja.getId() > 0) {
//				PutanjaDao.updateLokacije(putanja);
//			} else
//				PutanjaDao.insertPutanja(putanja);
//
//			return "lokacije.xhtml";
//
//		}
//		msg = "Podaci nisu ispravni.";
//		return null;
//
//	}

	public String save() {

		if (lokacije.stream().anyMatch(l -> l.getDrzava().equals(polaznaDrzava) && l.getGrad().equals(polazniGrad))
				&& lokacije.stream()
						.anyMatch(l -> l.getDrzava().equals(odredisnaDrzava) && l.getGrad().equals(odredisniGrad))) {
			Lokacija polazna = lokacije.stream()
					.filter(l -> l.getDrzava().equals(polaznaDrzava) && l.getGrad().equals(polazniGrad)).findFirst()
					.get();
			Lokacija odredisna = lokacije.stream()
					.filter(l -> l.getDrzava().equals(odredisnaDrzava) && l.getGrad().equals(odredisniGrad)).findFirst()
					.get();

			putanja.setPolaznaLokacija(polazna);
			putanja.setOdredisnaLokacija(odredisna);

			if (putanja.getPolaznaLokacija() != null && putanja.getOdredisnaLokacija() != null) {
				if (putanja.getId() > 0) {
					PutanjaDao.updateLokacije(putanja);
				} else
					PutanjaDao.insertPutanja(putanja);

				return "lokacije.xhtml";

			}
		}
		msg = "Podaci nisu ispravni.";
		return null;
	}
}

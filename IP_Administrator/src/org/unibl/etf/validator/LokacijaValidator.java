package org.unibl.etf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.unibl.etf.dao.PutanjaDao;

@FacesValidator("lokacijaValidator")

public class LokacijaValidator implements Validator {

	public LokacijaValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value == null || component.getAttributes().get("polaznaDrzava") == null
				|| component.getAttributes().get("polazniGrad") == null
				|| component.getAttributes().get("odredisnaDrzava") == null)
			return;

		String pDrzava = (String) component.getAttributes().get("polaznaDrzava");
		String oDrzava = (String) component.getAttributes().get("odredisnaDrzava");
		String pGrad = (String) component.getAttributes().get("polazniGrad");
		Boolean update = (Boolean) component.getAttributes().get("update");
		update = update != null ? update : false;
		String oGrad = (String) value;

		if (pDrzava.equals(oDrzava) && pGrad.equals(oGrad)) {
			FacesMessage message = new FacesMessage("*Lokacije ne mogu biti iste.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		} else if(!update && PutanjaDao.check(pDrzava, pGrad, oDrzava, oGrad) != null) {
			System.out.println("check");
			FacesMessage message = new FacesMessage("*Putanja sa izbranom polaznom i odredisnom lokacijom je vec definisana.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}

}

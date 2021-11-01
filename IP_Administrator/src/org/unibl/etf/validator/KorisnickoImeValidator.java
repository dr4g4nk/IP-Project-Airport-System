package org.unibl.etf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.unibl.etf.dao.KorisnikDao;
import org.unibl.etf.dto.Korisnik;

@FacesValidator("korisnickoImeValidator")
public class KorisnickoImeValidator implements Validator {

	public KorisnickoImeValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		String korisnickoIme = (String) value;
		Korisnik korisnik = KorisnikDao.getKorisnikByKorisnickoIme(korisnickoIme);
		if(korisnik!=null) {
			FacesMessage message = new FacesMessage("*Korisnicko ime je zauzeto.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
		}

	}

}

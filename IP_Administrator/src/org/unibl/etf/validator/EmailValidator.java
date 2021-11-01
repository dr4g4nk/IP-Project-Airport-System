package org.unibl.etf.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.unibl.etf.dao.KorisnikDao;
import org.unibl.etf.dto.Korisnik;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	public EmailValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		FacesMessage message = null;
		String email = (String) value;
		Pattern pattern = Pattern.compile("[a-zA-Z0-9\\-\\_]+\\@.+\\.[a-zA-Z0-9]+");
		Korisnik korisnik = KorisnikDao.getKorisnikById(email);
		if (korisnik != null || !pattern.matcher(email).matches()) {
			if (korisnik != null)
				message = new FacesMessage("*Email je zauzet.");
			else
				message = new FacesMessage("*Email nije odgovarujeceg formata: abcd@abc.com.");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}

	}

}

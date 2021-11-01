package org.unibl.etf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

	public PasswordValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value==null || component.getAttributes().get("lozinka") == null) return;
		
		String lozinka = (String) component.getAttributes().get("lozinka");
		String potvrdaLozinka = (String) value;
		
		if(!lozinka.equals(potvrdaLozinka)) {
			FacesMessage message = new FacesMessage("*Lozinke nisu iste.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
		}
		
	}

}

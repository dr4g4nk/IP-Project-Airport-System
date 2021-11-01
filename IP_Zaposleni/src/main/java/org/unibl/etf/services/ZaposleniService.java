package org.unibl.etf.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Zaposleni;
import org.unibl.etf.repositories.ZaposleniRepository;

@Service
public class ZaposleniService {

	@Autowired
	private ZaposleniRepository zaposleniRepository;
	
	public boolean login(Zaposleni zaposleni) {
		Optional<Zaposleni> optional = zaposleniRepository.findById(zaposleni.getKorisnickoIme());
		if(optional.isPresent()) {
			Zaposleni zaposleniDB = optional.get();
			zaposleni.generatePassword();
			if(zaposleniDB.getLozinka().equals(zaposleni.getLozinka()) && zaposleniDB.isEnabled() && zaposleniDB.getRole().equals("ROLE_USER"))
					return true;
		}
		return false;
	}

}

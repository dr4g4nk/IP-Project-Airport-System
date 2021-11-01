package org.unibl.etf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.entities.Zaposleni;
import org.unibl.etf.services.ZaposleniService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private ZaposleniService service;

	@PostMapping
	public ResponseEntity<?> login(@RequestBody Zaposleni zaposleni) {
		System.out.println("login");
		if (zaposleni.getKorisnickoIme() != null && zaposleni.getLozinka() != null) {
			if (service.login(zaposleni)) {
				zaposleni = new Zaposleni(zaposleni.getKorisnickoIme(), null, false, null);
				
				zaposleni.setLoggedIn(true);
				return ResponseEntity.ok(zaposleni);
			}
			return ResponseEntity.ok(zaposleni);
		}
		
		return ResponseEntity.badRequest().body(zaposleni);
	}

}

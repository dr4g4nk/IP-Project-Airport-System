package org.unibl.etf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.entities.Rezervacija;
import org.unibl.etf.services.RezervacijaService;

@RestController
@RequestMapping("/api/v1/rezervacije")
@CrossOrigin(origins = "http://localhost:4200")

public class RezervacijaController {

	@Autowired
	private RezervacijaService rezervacijaService;

	@GetMapping
	public ResponseEntity<List<Rezervacija>> getAllRezervacije() {
		List<Rezervacija> list = rezervacijaService.getAllRezervacije();
		return ResponseEntity.ok(list);
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody Rezervacija newRezervacija) {
		System.out.println(newRezervacija.getId());
		if (newRezervacija.getId() > 0 && newRezervacija.getStatus() != null && !newRezervacija.getStatus().isBlank()
				&& (newRezervacija.getStatus().equals("Prihvacena") || (newRezervacija.getStatus().equals("Ponistena")
						&& newRezervacija.getRazlog() != null && !newRezervacija.getRazlog().isBlank()))) {
			Rezervacija rezervacija = rezervacijaService.update(newRezervacija);

			return ResponseEntity.ok(rezervacija);
		}

		return ResponseEntity.badRequest().body("Niste proslijedili potrebne podatke.");
	}

}

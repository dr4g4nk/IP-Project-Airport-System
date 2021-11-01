package org.unibl.etf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.entities.Termin;
import org.unibl.etf.services.TerminService;

@RestController
@RequestMapping("/api/v1/termini")
@CrossOrigin(origins = "http://localhost:4200")

public class TerminController {

	@Autowired
	private TerminService terminService;

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Termin termin) {
		if (termin.getDan() != null && termin.getPolazak() != null && termin.getDolazak() != null) {
			Termin t = terminService.insert(termin);
			return ResponseEntity.ok(t);
		}

		return ResponseEntity.badRequest().body("Niste proslijedili potrebne podatke.");
	}

	@GetMapping()
	public ResponseEntity<List<Termin>> getTerminiByLet(@RequestParam(name = "id") long id) {
		return ResponseEntity.ok(terminService.getTerminiByLet(id));
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTermin(@RequestParam(name = "id") long id) {
		System.out.println("Obrisi termin");
		terminService.delete(id);
		return ResponseEntity.ok().build();
	}
}

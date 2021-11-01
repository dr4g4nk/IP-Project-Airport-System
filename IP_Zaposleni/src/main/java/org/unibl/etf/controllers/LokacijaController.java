package org.unibl.etf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.entities.Lokacija;
import org.unibl.etf.services.LokacijaService;

@RestController
@RequestMapping("/api/v1/lokacije")
@CrossOrigin(origins = "http://localhost:4200")

public class LokacijaController {

	@Autowired
	private LokacijaService lokacijaService;

	@GetMapping
	public ResponseEntity<List<Lokacija>> getAllLokacije(){
		List<Lokacija> list = lokacijaService.getAllLokacije();
		return ResponseEntity.ok(list);
	}
}

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
import org.unibl.etf.entities.Poruka;
import org.unibl.etf.services.PorukaService;

@RestController
@RequestMapping("/api/v1/poruke")
@CrossOrigin(origins = "http://localhost:4200")

public class PorukaContoller {

	@Autowired
	private PorukaService porukaService;
	
	@GetMapping
	public ResponseEntity<List<Poruka>> getAllPoruke(){
		List<Poruka> list = porukaService.getAllPoruka();
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	public ResponseEntity<Poruka> update(@RequestBody Poruka newPoruka){
		System.out.println("controller update "+newPoruka.getEmail());
		Poruka poruka = porukaService.update(newPoruka.getId());
		return ResponseEntity.accepted().body(poruka);
	}

}

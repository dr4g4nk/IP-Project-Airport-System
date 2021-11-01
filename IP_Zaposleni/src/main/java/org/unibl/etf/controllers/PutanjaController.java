package org.unibl.etf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.entities.Putanja;
import org.unibl.etf.services.PutanjaService;

@RestController
@RequestMapping("/api/v1/putanja")
@CrossOrigin(origins = "http://localhost:4200")

public class PutanjaController {

	@Autowired
	private PutanjaService putanjaService;
	
	@GetMapping
	public ResponseEntity<List<Putanja>> getPutanje(){
		return ResponseEntity.ok(putanjaService.getPutanje());
	}

}

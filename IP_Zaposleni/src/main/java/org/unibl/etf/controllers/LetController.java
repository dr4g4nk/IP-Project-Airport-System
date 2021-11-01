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
import org.unibl.etf.entities.Let;
import org.unibl.etf.services.LetService;

@RestController
@RequestMapping("/api/v1/letovi")
@CrossOrigin(origins = "http://localhost:4200")

public class LetController {

	@Autowired
	private LetService letService;

	@GetMapping
	public ResponseEntity<List<Let>> getAllLetovi() {
		System.out.println("Letovi");
		List<Let> list = letService.getAllLetovi();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody Let let) {
		System.out.println(let.getPutanja());
		if (let != null && let.getPutanja() != null && let.getPutanja().getId() > 0) {
			let = letService.insert(let);
			return ResponseEntity.ok(let);
		}

		return ResponseEntity.badRequest().body("Niste poslali potrebne podatke");
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam(name="id") long id) {
		if (id > 0) {
			letService.delete(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().body("Niste proslijedili ispravan id");
	}
}

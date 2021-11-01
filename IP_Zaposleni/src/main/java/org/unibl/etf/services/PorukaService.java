package org.unibl.etf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Poruka;
import org.unibl.etf.repositories.PorukaRepository;

@Service
public class PorukaService {
	
	@Autowired
	private PorukaRepository porukaRepository;
	
	public List<Poruka> getAllPoruka(){
		return porukaRepository.findAll();
	}
	
	public Poruka update(Long id) {
		Optional<Poruka> optional = porukaRepository.findById(id);
		
		if(optional.isPresent()) {
			System.out.println("Update");
			Poruka poruka = optional.get();
			poruka.setStatus("Procitana");
			return porukaRepository.save(poruka);
		}
		else
			return null;
	}
}

package org.unibl.etf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Termin;
import org.unibl.etf.repositories.TerminRepository;

@Service
public class TerminService {

	@Autowired
	private TerminRepository terminRepository;
	
	public List<Termin> getTerminiByLet(Long letId){
		return terminRepository.findAllByLet(letId);
	}
	
	public Termin insert(Termin termin) {
		Termin terminDB = terminRepository.save(termin);
		return terminDB;
	}
	
	public void delete(long id) {
		System.out.println("delete");
		Optional<Termin> optional = terminRepository.findById(id);
		System.out.println(optional.isPresent());

		if(optional.isPresent()) {
			terminRepository.delete(optional.get());
		}
	}
}

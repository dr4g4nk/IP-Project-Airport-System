package org.unibl.etf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Rezervacija;
import org.unibl.etf.repositories.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	RezervacijaRepository rezervacijaRepository;
	
	public List<Rezervacija> getAllRezervacije(){
		return rezervacijaRepository.findAll();
	}
	
	public Rezervacija update(Rezervacija newRezervacija) {
		Optional<Rezervacija> optional = rezervacijaRepository.findById(newRezervacija.getId());
		if(optional.isPresent()) {
			Rezervacija rezervacija = optional.get();
			rezervacija.setStatus(newRezervacija.getStatus());
			rezervacija.setRazlog(newRezervacija.getRazlog());
			System.out.println("Update");
			return rezervacijaRepository.save(rezervacija);
		}
		return null;
	}
}

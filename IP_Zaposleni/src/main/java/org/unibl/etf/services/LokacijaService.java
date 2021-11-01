package org.unibl.etf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Lokacija;
import org.unibl.etf.repositories.LokacijaRepository;

@Service
public class LokacijaService {

	@Autowired
	private LokacijaRepository lokacijaRepository;
	
	public List<Lokacija> getAllLokacije(){
		return lokacijaRepository.findAll();
	}

}

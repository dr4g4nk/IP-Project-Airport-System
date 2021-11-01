package org.unibl.etf.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Putanja;
import org.unibl.etf.repositories.PutanjaRepository;

@Service
public class PutanjaService {

	@Autowired
	private PutanjaRepository putanjaRepository;

	public List<Putanja> getPutanje(){
		return putanjaRepository.findAll();
	}
	
}

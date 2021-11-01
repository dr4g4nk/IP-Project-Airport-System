package org.unibl.etf.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.entities.Let;
import org.unibl.etf.repositories.LetRepository;

@Service
public class LetService {

	@Autowired
	private LetRepository letRepository;

	public List<Let> getAllLetovi() {
		return letRepository.findAll();
	}

	public Let insert(Let let) {
		return letRepository.save(let);
	}

	public Let getLetById(long id) {
		Optional<Let> optional = letRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	public void delete(long id) {
		letRepository.deleteById(id);
	}
}

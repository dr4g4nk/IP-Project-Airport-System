package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.entities.Lokacija;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {

}

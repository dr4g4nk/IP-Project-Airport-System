package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unibl.etf.entities.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

	@Query("delete from Rezervacija r where r.termin.id=:id")
	void deleteByTermin(@Param("id") long id);
}

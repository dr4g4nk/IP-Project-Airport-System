package org.unibl.etf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.unibl.etf.entities.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long> {

	@Query("select t from Termin t where t.l.id=:letId")
	List<Termin> findAllByLet(@Param("letId") long letId);
}

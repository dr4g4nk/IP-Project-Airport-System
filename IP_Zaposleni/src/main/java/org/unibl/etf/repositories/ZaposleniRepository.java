package org.unibl.etf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.entities.Zaposleni;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, String> {

}

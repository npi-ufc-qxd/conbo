package br.ufc.conbo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ufc.conbo.model.TipoBolsa;

@Repository
@Transactional
public interface TipoBolsaRepository extends JpaRepository<TipoBolsa, Long>{

}

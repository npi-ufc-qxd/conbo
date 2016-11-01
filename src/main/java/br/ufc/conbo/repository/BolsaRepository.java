package br.ufc.conbo.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.ufc.conbo.model.Bolsa;

@Repository
@Transactional
public interface BolsaRepository extends JpaRepository<Bolsa, Long>{

}

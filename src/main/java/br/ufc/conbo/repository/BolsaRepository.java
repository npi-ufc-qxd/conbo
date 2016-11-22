package br.ufc.conbo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufc.conbo.model.Bolsa;

@Repository
@Transactional
public interface BolsaRepository extends JpaRepository<Bolsa, Long>{

	 Bolsa findByNome(String nome);
	 
	 @Query("from Bolsa b where b.projeto is null")
	 List<Bolsa> buscarBolsasNaoAssociadas();
	 
}

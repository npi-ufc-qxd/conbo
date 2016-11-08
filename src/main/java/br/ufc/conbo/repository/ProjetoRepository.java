package br.ufc.conbo.repository;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.ufc.conbo.model.Projeto;

@Repository
@Transactional
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
	
	@Query("from Projeto p where (lower(p.nome) like :busca)")
	List<Projeto> buscarPorNome(@Param("busca") String busca);

}

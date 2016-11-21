package br.ufc.conbo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.ufc.conbo.model.Bolsa;
import br.ufc.conbo.model.Projeto;

@Service
public interface BolsaService {
	
	void salvar(Bolsa bolsa);
	
	void editar(Bolsa bolsa);
	
	void remover(Long id);
	
	Bolsa buscarPorId(Long id);
	
	List<Bolsa> listar();
	
	Bolsa buscarPorNome(String nome);
	
	List<Bolsa> buscarBolsasNaoAssociadas();
	
}
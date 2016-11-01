package br.ufc.conbo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.ufc.conbo.model.TipoBolsa;

@Service
public interface TipoBolsaService {
	
	void salvar(TipoBolsa tipoBolsa);
	
	void editar(TipoBolsa tipoBolsa);
	
	void remover(Long id);
	
	TipoBolsa buscarPorId(Long id);
	
	List<TipoBolsa> listar();
	
}
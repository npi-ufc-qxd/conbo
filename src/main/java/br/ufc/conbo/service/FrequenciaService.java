package br.ufc.conbo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.ufc.conbo.model.Frequencia;

@Service
public interface FrequenciaService {
	
	void salvar(Frequencia frequencia);
	
	void editar(Frequencia frequencia);
	
	void remover(Long id);
	
	Frequencia buscarPorId(Long id);
	
	List<Frequencia> listar();
	
}
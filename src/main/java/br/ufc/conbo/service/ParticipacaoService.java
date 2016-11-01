package br.ufc.conbo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.ufc.conbo.model.Participacao;

@Service
public interface ParticipacaoService {
	
	void salvar(Participacao participacao);
	
	void editar(Participacao participacao);
	
	void remover(Long id);
	
	Participacao buscarPorId(Long id);
	
	List<Participacao> listar();
	
}
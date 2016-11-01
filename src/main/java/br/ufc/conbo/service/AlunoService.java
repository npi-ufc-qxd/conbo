package br.ufc.conbo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.ufc.conbo.model.Aluno;

@Service
public interface AlunoService {
	
	void salvar(Aluno aluno);
	
	void editar(Aluno aluno);
	
	void remover(Long id);
	
	Aluno buscarPorId(Long id);
	
	List<Aluno> listar();
	
}

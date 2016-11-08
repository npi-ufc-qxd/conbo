package br.ufc.conbo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.conbo.model.Projeto;

@Service
public interface ProjetoService {
	
	void salvar(Projeto projeto);
	
	void editar(Projeto projeto);
	
	void remover(Long id);
	
	Projeto buscarPorId(Long id);
	
	List<Projeto> buscarPorNome(String busca);
	
	List<Projeto> listar();
	
}
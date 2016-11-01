package br.ufc.conbo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufc.conbo.model.Pessoa;

@Service
public interface PessoaService {
	
	void salvar(Pessoa pessoa);
	
	void editar(Pessoa pessoa);
	
	void remover(Long id);
	
	Pessoa buscarPorId(Long id);
	
	List<Pessoa> listar();
	
}

package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.Pessoa;
import br.ufc.conbo.repository.PessoaRepository;
import br.ufc.conbo.service.PessoaService;

@Named
public class PessoaServiceImpl implements PessoaService{

	@Inject
	private PessoaRepository pessoaRepository;
	
	@Override
	public void salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	@Override
	public void editar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}

	@Override
	public void remover(Long id) {
		Pessoa pessoa = pessoaRepository.findOne(id);
		pessoaRepository.delete(pessoa);
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		return pessoaRepository.findOne(id);
	}

	@Override
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

}

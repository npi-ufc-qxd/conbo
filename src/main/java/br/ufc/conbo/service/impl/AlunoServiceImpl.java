package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.Aluno;
import br.ufc.conbo.repository.AlunoRepository;
import br.ufc.conbo.service.AlunoService;

@Named
public class AlunoServiceImpl implements AlunoService{

	@Inject
	private AlunoRepository alunoRepository;
	
	@Override
	public void salvar(Aluno aluno) {
		alunoRepository.save(aluno);
	}

	@Override
	public void editar(Aluno aluno) {
		alunoRepository.save(aluno);
	}

	@Override
	public void remover(Long id) {
		Aluno aluno = alunoRepository.findOne(id);
		alunoRepository.delete(aluno);
	}

	@Override
	public Aluno buscarPorId(Long id) {
		return alunoRepository.findOne(id);
	}

	@Override
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}

}

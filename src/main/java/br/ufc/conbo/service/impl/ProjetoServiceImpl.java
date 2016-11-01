package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.Projeto;
import br.ufc.conbo.repository.ProjetoRepository;
import br.ufc.conbo.service.ProjetoService;

@Named
public class ProjetoServiceImpl implements ProjetoService{

	@Inject
	private ProjetoRepository projetoRepository;
	
	@Override
	public void salvar(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	@Override
	public void editar(Projeto projeto) {
		projetoRepository.save(projeto);
	}

	@Override
	public void remover(Long id) {
		Projeto projeto = projetoRepository.findOne(id);
		projetoRepository.delete(projeto);
	}

	@Override
	public Projeto buscarPorId(Long id) {
		return projetoRepository.findOne(id);
	}

	@Override
	public List<Projeto> listar() {
		return projetoRepository.findAll();
	}

}

package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.Participacao;
import br.ufc.conbo.repository.ParticipacaoRepository;
import br.ufc.conbo.service.ParticipacaoService;

@Named
public class ParticipacaoServiceImpl implements ParticipacaoService{

	@Inject
	private ParticipacaoRepository participacaoRepository;
	
	@Override
	public void salvar(Participacao participacao) {
		participacaoRepository.save(participacao);
	}

	@Override
	public void editar(Participacao participacao) {
		participacaoRepository.save(participacao);
	}

	@Override
	public void remover(Long id) {
		Participacao participacao = participacaoRepository.findOne(id);
		participacaoRepository.delete(participacao);
	}

	@Override
	public Participacao buscarPorId(Long id) {
		return participacaoRepository.findOne(id);
	}

	@Override
	public List<Participacao> listar() {
		return participacaoRepository.findAll();
	}

}

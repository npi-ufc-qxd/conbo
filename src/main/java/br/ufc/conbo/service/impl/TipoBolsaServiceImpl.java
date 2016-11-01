package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.TipoBolsa;
import br.ufc.conbo.repository.TipoBolsaRepository;
import br.ufc.conbo.service.TipoBolsaService;

@Named
public class TipoBolsaServiceImpl implements TipoBolsaService{

	@Inject
	private TipoBolsaRepository tipoBolsaRepository;
	
	@Override
	public void salvar(TipoBolsa tipoBolsa) {
		tipoBolsaRepository.save(tipoBolsa);
	}

	@Override
	public void editar(TipoBolsa tipoBolsa) {
		tipoBolsaRepository.save(tipoBolsa);
	}

	@Override
	public void remover(Long id) {
		TipoBolsa tipoBolsa = tipoBolsaRepository.findOne(id);
		tipoBolsaRepository.delete(tipoBolsa);
	}

	@Override
	public TipoBolsa buscarPorId(Long id) {
		return tipoBolsaRepository.findOne(id);
	}

	@Override
	public List<TipoBolsa> listar() {
		return tipoBolsaRepository.findAll();
	}

}

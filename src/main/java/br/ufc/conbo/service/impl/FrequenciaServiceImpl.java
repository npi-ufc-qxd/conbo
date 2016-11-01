package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import br.ufc.conbo.model.Frequencia;
import br.ufc.conbo.repository.FrequenciaRepository;
import br.ufc.conbo.service.FrequenciaService;

@Named
public class FrequenciaServiceImpl implements FrequenciaService{

	@Inject
	private FrequenciaRepository frequenciaRepository;
	
	@Override
	public void salvar(Frequencia frequencia) {
		frequenciaRepository.save(frequencia);
	}

	@Override
	public void editar(Frequencia frequencia) {
		frequenciaRepository.save(frequencia);
	}

	@Override
	public void remover(Long id) {
		Frequencia frequencia = frequenciaRepository.findOne(id);
		frequenciaRepository.delete(frequencia);
	}

	@Override
	public Frequencia buscarPorId(Long id) {
		return frequenciaRepository.findOne(id);
	}

	@Override
	public List<Frequencia> listar() {
		return frequenciaRepository.findAll();
	}

}

package br.ufc.conbo.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.ufc.conbo.model.Bolsa;
import br.ufc.conbo.repository.BolsaRepository;
import br.ufc.conbo.service.BolsaService;

@Named
public class BolsaServiceImpl implements BolsaService{

	@Inject
	private BolsaRepository bolsaRepository;
	
	@Override
    public Bolsa buscarPorNome(String nome) {
        return bolsaRepository.findByNome(nome);
    }
	
	@Override
	public void salvar(Bolsa bolsa) {
		bolsaRepository.save(bolsa);
	}

	@Override
	public void editar(Bolsa bolsa) {
		bolsaRepository.save(bolsa);
	}

	@Override
	public void remover(Long id) {
		Bolsa bolsa = bolsaRepository.findOne(id);
		bolsaRepository.delete(bolsa);
	}

	@Override
	public Bolsa buscarPorId(Long id) {
		return bolsaRepository.findOne(id);
	}

	@Override
	public List<Bolsa> listar() {
		return bolsaRepository.findAll();
	}

	@Override
	public List<Bolsa> buscarBolsasNaoAssociadas() {
		return bolsaRepository.buscarBolsasNaoAssociadas();
	}

}

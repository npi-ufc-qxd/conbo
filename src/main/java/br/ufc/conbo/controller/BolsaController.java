package br.ufc.conbo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.Aluno;
import br.ufc.conbo.model.Bolsa;
import br.ufc.conbo.model.Participacao;
import br.ufc.conbo.model.Projeto;
import br.ufc.conbo.service.AlunoService;
import br.ufc.conbo.service.BolsaService;
import br.ufc.conbo.service.ParticipacaoService;
import br.ufc.conbo.service.PessoaService;
import br.ufc.conbo.service.ProjetoService;
import br.ufc.conbo.service.TipoBolsaService;

@Controller
@RequestMapping("bolsa")
public class BolsaController {

	@Inject
	private BolsaService bolsaService;
	

	@Inject
	private AlunoService alunoService;

	@Inject
	private TipoBolsaService tipoBolsaService;

	@Inject
	private PessoaService pessoaService;
	
	@Inject
	private ParticipacaoService participacaoservice;
	
	@Inject
	private ProjetoService projetoservice;
	


	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){

		ModelAndView modelAndView = new ModelAndView("/views/bolsa/cadastrar");
		modelAndView.addObject("bolsa", new Bolsa());
		modelAndView.addObject("acao", "CADASTRAR");
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
		modelAndView.addObject("pessoas", this.pessoaService.listar());

		return modelAndView;
	}

	@RequestMapping(value="/buscar/{nome}", method = RequestMethod.GET)
	public ModelAndView buscarForm(@PathVariable("nome") String nome){
		ModelAndView model = new ModelAndView("/views/bolsa/listar");
		Bolsa bolsa = this.bolsaService.buscarPorNome(nome);
		List<Bolsa> bolsas = new ArrayList<>();
		bolsas.add(bolsa);

		model.addObject("bolsas", bolsas);
		return model;
	}

	@RequestMapping(value = "/buscar/", method = RequestMethod.POST)
	public ModelAndView buscarPorNome(@Valid Bolsa bolsa){
		ModelAndView modelAndView = new ModelAndView("redirect:" +bolsa.getNome());
		return modelAndView;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute("bolsa") Bolsa bolsa) {
		bolsaService.salvar(bolsa);
		return "redirect:listar";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/listar");
		modelAndView.addObject("bolsas", this.bolsaService.listar());

		return modelAndView;
	}

	@RequestMapping(value = "/detalhes/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalhes (@PathVariable("id") Long id){
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/detalhes");
		
		Bolsa bolsa = this.bolsaService.buscarPorId(id);
		
		List<Participacao> partipacoes = bolsa.getParticipacoes();
		List<Participacao> inativos = new ArrayList<>();
		List<Participacao> ativos = new ArrayList<>();
		
		Participacao participacao;
		
	
		for (int i = 0; i <  partipacoes.size() ; i++) {
			participacao = partipacoes.get(i);
			if (! participacao.isStatus()){
				inativos.add(participacao);
			}else
				ativos.add(participacao);
		}
		
		
		
		bolsa.setParticipacoes(ativos);
		
		modelAndView.addObject("bolsa", bolsa);
		modelAndView.addObject("inativos", inativos);


		return modelAndView;
	}

	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Long id){
		this.bolsaService.remover(id);

		ModelAndView modelAndView = new ModelAndView("/views/bolsa/listar");
		modelAndView.addObject("bolsas", this.bolsaService.listar());

		return modelAndView;
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long id){

		Bolsa bolsa = bolsaService.buscarPorId(id);
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/editar");

		if(bolsa==null){
			return null;
		}

		modelAndView.addObject("bolsa", bolsa);
		modelAndView.addObject("acao", "EDITAR");
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
		modelAndView.addObject("pessoas", this.pessoaService.listar());
		modelAndView.addObject("projetos", this.projetoservice.listar());
		
		System.out.println(bolsa.getResponsaveis().size());
		return modelAndView ;
	}

	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid Bolsa bolsa){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.bolsaService.editar(bolsa);
		
		modelAndView.addObject("bolsas", this.bolsaService.listar());
		return modelAndView;
	}

	@RequestMapping(value = "/encerrarBolsista/{idBolsa}/{idAluno}", method = RequestMethod.GET)
	public ModelAndView encerrarbolsa(@PathVariable("idBolsa") Long idBolsa, @PathVariable("idAluno") Long idAluno){

		Aluno aluno = alunoService.buscarPorId(idAluno);
		List<Participacao> partipacoes = aluno.getParticipacoes();
		Participacao participacao = null;
		
		for (int i = 0; i < partipacoes.size(); i++) {
			Bolsa bolsa = partipacoes.get(i).getBolsa();

			if (bolsa.getId() == idBolsa && partipacoes.get(i).isStatus()){
				participacao = partipacoes.get(i);
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/bolsa/encerrarParticipacao/"+participacao.getId());
	

		return modelAndView ;
	}
	
	
	@RequestMapping(value = "/encerrarParticipacao/{id}", method = RequestMethod.GET)
	public ModelAndView encerrarParticipacaoForm (@PathVariable("id") Long idParticipacao){
		Participacao participacao = participacaoservice.buscarPorId(idParticipacao);
		ModelAndView modelAndView = new ModelAndView("/views/participacao/encerrar");

		
		modelAndView.addObject("participacao", participacao);
		
		return modelAndView ;
	}
	
	
	@RequestMapping(value = "/encerrarParticipacao", method = RequestMethod.POST)
	
	public ModelAndView encerarParticipacao(Participacao participacao){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		
		Participacao partici2 = participacaoservice.buscarPorId(participacao.getId());
		partici2.setStatus(false);
		partici2.setDataFim(participacao.getDataFim());
		this.participacaoservice.editar(partici2);
		modelAndView.addObject("bolsas", this.bolsaService.listar());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/encerrarBolsa/{id}", method = RequestMethod.GET)
	public ModelAndView encerrarBolsaForm (@PathVariable("id") Long idBolsa){
		Bolsa bolsa = bolsaService.buscarPorId(idBolsa);
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/encerrar");

		
		modelAndView.addObject("bolsa", bolsa);
		
		return modelAndView ;
	}
	
	
	@RequestMapping(value = "/encerrarBolsa", method = RequestMethod.POST)
	public ModelAndView encerarBolsa(Bolsa bolsa){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		
		//A bolsa q vinha do encerrarBolsaForm não continha as participacoes
		Bolsa bolsa2 = bolsaService.buscarPorId(bolsa.getId());
		
		List<Participacao> partici = bolsa2.getParticipacoes();
		
		for (Participacao participacao : partici) {
			if (participacao.isStatus()){
				participacao.setStatus(false);
				participacao.setDataFim(bolsa.getDataFim());
				participacao.setObservacao("A bolsa foi encerrada.");
			}
		}
		bolsa2.setParticipacoes(partici);
		bolsa2.setDataFim(bolsa.getDataFim());
		bolsa2.setObservacao(bolsa.getObservacao());
		bolsa2.setStatus(false);
		//bolsa.setParticipacoes(partici);
		
		
		this.bolsaService.editar(bolsa2);
		modelAndView.addObject("bolsas", this.bolsaService.listar());
		return modelAndView;
	}
	

}

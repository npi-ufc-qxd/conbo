package br.ufc.conbo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.Aluno;
import br.ufc.conbo.model.Bolsa;
import br.ufc.conbo.model.Participacao;
import br.ufc.conbo.service.AlunoService;
import br.ufc.conbo.service.BolsaService;
import br.ufc.conbo.service.ParticipacaoService;
import br.ufc.conbo.service.PessoaService;
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
		Participacao partici;
		for (int i = 0; i < partipacoes.size(); i++) {
			partici = partipacoes.get(i);
			if (partici.getDataFim() != null){
				partipacoes.remove(i);
				inativos.add(partici);
			}
		}
		bolsa.setParticipacoes(partipacoes);
		
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
			if (bolsa.getId() == idBolsa && partipacoes.get(i).getDataFim() == null){
				participacao = partipacoes.get(i);
				break;
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/bolsa/encerrarParticipacao/"+participacao.getId());
	

		return modelAndView ;
	}
	
	
	@RequestMapping(value = "/encerrarParticipacao/{id}", method = RequestMethod.GET)
	public ModelAndView encerrarForm (@PathVariable("id") Long idParticipacao){
		Participacao participacao = participacaoservice.buscarPorId(idParticipacao);
		ModelAndView modelAndView = new ModelAndView("/views/participacao/encerrar");

		participacao.setObservacao("Um teste aqui");
		
		modelAndView.addObject("participacao", participacao);
		
		return modelAndView ;
	}
	
	
	@RequestMapping(value = "/encerrarParticipacao", method = RequestMethod.POST)
	public ModelAndView encerar(Participacao participacao){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.participacaoservice.editar(participacao);
		modelAndView.addObject("bolsas", this.bolsaService.listar());
		return modelAndView;
	}
	
	@RequestMapping(value = "/folhaPagamento/{id}", method = RequestMethod.GET)
	public ModelAndView folhaPagamento(@PathVariable("id") Long id){

		ModelAndView modelAndView = new ModelAndView("/views/bolsa/folhaPagamento");
		Bolsa bolsa = bolsaService.buscarPorId(id);
		modelAndView.addObject("bolsa", bolsa);

		return modelAndView;
	}


}

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

import br.ufc.conbo.model.Bolsa;

import br.ufc.conbo.service.BolsaService;
import br.ufc.conbo.service.PessoaService;
import br.ufc.conbo.service.TipoBolsaService;

@Controller
@RequestMapping("bolsa")
public class BolsaController {

	@Inject
	private BolsaService bolsaService;

	@Inject
	private TipoBolsaService tipoBolsaService;

	@Inject
	private PessoaService pessoaService;


	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){
		System.err.println("Aqui");

		ModelAndView modelAndView = new ModelAndView("/views/bolsa/cadastrar");
		modelAndView.addObject("bolsa", new Bolsa());
		modelAndView.addObject("acao", "CADASTRAR");
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
		modelAndView.addObject("pessoas", this.pessoaService.listar());

		return modelAndView;
	}

	@RequestMapping(value="/buscar/{nome}", method = RequestMethod.GET)
	public ModelAndView buscarForm(@PathVariable("nome") String nome){
		System.err.println("AQUI");
		ModelAndView model = new ModelAndView("/views/bolsa/listar");
		Bolsa bolsa = this.bolsaService.buscarPorNome(nome);
		System.err.println("AQUI");
		List<Bolsa> bolsas = new ArrayList<>();
		bolsas.add(bolsa);

		model.addObject("bolsas", bolsas);
		System.err.println("AQUI");
		return model;
	}

	@RequestMapping(value = "/buscar/", method = RequestMethod.POST)
	public ModelAndView buscarPorNome(@Valid Bolsa bolsa){
		ModelAndView modelAndView = new ModelAndView("redirect:" +bolsa.getNome());
		return modelAndView;
	}



	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute("bolsa") Bolsa bolsa) {

		//bolsa.getTipoBolsa().setIdTipoBolsa(bolsa.getIdBolsa());

		System.err.println("Aqui");
		//System.err.println(bolsa.toString());
		bolsaService.salvar(bolsa);
		return "redirect:listar";
	}


	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/listar");
		modelAndView.addObject("bolsas", this.bolsaService.listar());

		return modelAndView;
	}

	@RequestMapping(value = "/verDetalhes/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalhes (@PathVariable("id") Long idBolsa){
		ModelAndView modelAndView = new ModelAndView("/views/bolsa/ver_detalhes");
		System.err.println(this.bolsaService.buscarPorId(idBolsa).getParticipacoes().size());
		modelAndView.addObject("bolsa", this.bolsaService.buscarPorId(idBolsa));

		return modelAndView;
	}

	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Long idBolsa){
		this.bolsaService.remover(idBolsa);

		ModelAndView modelAndView = new ModelAndView("/views/bolsa/listar");
		modelAndView.addObject("bolsas", this.bolsaService.listar());

		return modelAndView;
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long idBolsa){

		Bolsa bolsa = bolsaService.buscarPorId(idBolsa);
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



}
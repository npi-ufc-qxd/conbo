package br.ufc.conbo.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.TipoBolsa;
import br.ufc.conbo.service.TipoBolsaService;

@Controller
@RequestMapping("tipoBolsa")
public class TipoBolsaController {

	@Inject
	private TipoBolsaService tipoBolsaService;
	

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){
		ModelAndView modelAndView = new ModelAndView("/views/tipo_bolsa/cadastrar");
		modelAndView.addObject("tipoBolsa", new TipoBolsa());
		modelAndView.addObject("acao", "CADASTRAR");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute("tipoBolsa") TipoBolsa tipoBolsa) {
		tipoBolsaService.salvar(tipoBolsa);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/tipo_bolsa/listar");
		
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
			
		return modelAndView;
	}
	
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Long idTipoBolsa){
		this.tipoBolsaService.remover(idTipoBolsa);
		
		ModelAndView modelAndView = new ModelAndView("/views/tipo_bolsa/listar");
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long idTipoBolsa){
		
		TipoBolsa tipoBolsa = tipoBolsaService.buscarPorId(idTipoBolsa);
		ModelAndView modelAndView = new ModelAndView("/views/tipo_bolsa/editar");
		
		if(tipoBolsa==null){
			return null;
		}
		
		modelAndView.addObject("tipoBolsa", tipoBolsa);
		modelAndView.addObject("acao", "EDITAR");
		return modelAndView ;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid TipoBolsa tipoBolsa){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.tipoBolsaService.editar(tipoBolsa);
		modelAndView.addObject("tipoBolsas", this.tipoBolsaService.listar());
		return modelAndView;
	}
	
	
	
}

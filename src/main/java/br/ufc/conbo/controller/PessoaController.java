package br.ufc.conbo.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.Pessoa;
import br.ufc.conbo.service.PessoaService;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

	@Inject
	private PessoaService pessoaService;
	

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){
		ModelAndView modelAndView = new ModelAndView("/views/pessoa/cadastrar");
		modelAndView.addObject("pessoa", new Pessoa());
		modelAndView.addObject("acao", "CADASTRAR");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaService.salvar(pessoa);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/pessoa/listar");
		
		modelAndView.addObject("pessoas", this.pessoaService.listar());
			
		return modelAndView;
	}
	
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Long idPessoa){
		this.pessoaService.remover(idPessoa);
		
		ModelAndView modelAndView = new ModelAndView("/views/pessoa/listar");
		modelAndView.addObject("pessoas", this.pessoaService.listar());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long idPessoa){
		
		Pessoa pessoa = pessoaService.buscarPorId(idPessoa);
		ModelAndView modelAndView = new ModelAndView("/views/pessoa/editar");
		
		if(pessoa==null){
			return null;
		}
		
		modelAndView.addObject("pessoa", pessoa);
		modelAndView.addObject("acao", "EDITAR");
		return modelAndView ;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid Pessoa pessoa){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.pessoaService.editar(pessoa);
		modelAndView.addObject("pessoas", this.pessoaService.listar());
		return modelAndView;
	}
	
	
	
}

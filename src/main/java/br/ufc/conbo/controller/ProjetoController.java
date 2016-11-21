package br.ufc.conbo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.Bolsa;
import br.ufc.conbo.model.Projeto;
import br.ufc.conbo.service.BolsaService;
import br.ufc.conbo.service.ProjetoService;

@Controller
@RequestMapping("projeto")
public class ProjetoController {
	

	@Inject
	private ProjetoService projetoService;
	
	@Inject
	private BolsaService bolsaService;	


	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){
		ModelAndView modelAndView = new ModelAndView("/views/projeto/cadastrar");
		modelAndView.addObject("projeto", new Projeto());
		modelAndView.addObject("acao", "CADASTRAR");
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute("projeto") Projeto projeto) {
		projetoService.salvar(projeto);
		return "redirect:listar";
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/projeto/listar");
		modelAndView.addObject("projetos", projetoService.listar());
		return modelAndView;
	}
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public ModelAndView listar (@RequestParam("busca") String busca){
		ModelAndView modelAndView = new ModelAndView("/views/projeto/listar");
		modelAndView.addObject("projetos", projetoService.buscarPorNome("%"+busca.toLowerCase()+"%"));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public String remover(@PathVariable("id") Long idProjeto){
		projetoService.remover(idProjeto);
		return "redirect:/projeto/listar";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long idProjeto){
		Projeto projeto = projetoService.buscarPorId(idProjeto);
		ModelAndView modelAndView = new ModelAndView("/views/projeto/editar");
		if(projeto==null){
			return null;
		}
		modelAndView.addObject("projeto", projeto);
		modelAndView.addObject("acao", "EDITAR");
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid Projeto projeto){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.projetoService.editar(projeto);
		modelAndView.addObject("projetos", projetoService.listar());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/detalhes/{id}", method = RequestMethod.GET)
	public ModelAndView visualizar(@PathVariable("id") Long idProjeto){
		
		ModelAndView modelAndView = new ModelAndView("/views/projeto/detalhes");
		modelAndView.addObject("bolsasNaoAssociadas", bolsaService.buscarBolsasNaoAssociadas());
		modelAndView.addObject("projeto", projetoService.buscarPorId(idProjeto));
		modelAndView.addObject("bolsas", bolsaService.listar());
		
		return modelAndView; 
	}

	@RequestMapping(value = "/{idProjeto}/associar/bolsa/{idBolsa}", method = RequestMethod.GET)
	public String associarBolsa(@PathVariable("idProjeto") Long idProjeto, 
			@PathVariable("idBolsa") Long idBolsa){
		Bolsa bolsa = bolsaService.buscarPorId(idBolsa);
		Projeto projeto = projetoService.buscarPorId(idProjeto);
		List<Bolsa> bolsas = projeto.getBolsas();
		bolsa.setProjeto(projeto);
		bolsas.add(bolsa);
		projeto.setBolsas(bolsas);
		projetoService.salvar(projeto);
		
		
		ModelAndView modelAndView = new ModelAndView("/views/projeto/detalhes");
		modelAndView.addObject("projeto", projetoService.buscarPorId(idProjeto));
		modelAndView.addObject("bolsas", bolsaService.listar());
		
		//return modelAndView;
		return "redirect:/projeto/detalhes/"+idProjeto;
	}

}

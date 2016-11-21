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
import br.ufc.conbo.service.AlunoService;

@Controller
@RequestMapping("aluno")
public class AlunoController {
	
	@Inject
	private AlunoService alunoService;
	
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public ModelAndView cadastrarForm(){
		ModelAndView model = new ModelAndView("/views/aluno/cadastrar");
		model.addObject("aluno", new Aluno());
		model.addObject("acao", "CADASTRAR");
		return model;
	}

	@RequestMapping(value="/cadastrar", method = RequestMethod.POST)
	public String cadastrar(@ModelAttribute ("aluno") Aluno aluno){
		this.alunoService.salvar(aluno);
		return "redirect:listar";
	}
	
	@RequestMapping(value="/detalhes/{id}", method = RequestMethod.GET)
	public ModelAndView detalhes(@PathVariable("id") Long id){
		ModelAndView model = new ModelAndView("/views/aluno/detalhes");
		Aluno aluno = this.alunoService.buscarPorId(id);
		model.addObject("aluno", aluno);
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(aluno);
		model.addObject("alunos", alunos);
		return model;
	}
	
	@RequestMapping(value="/buscar/{nome}", method = RequestMethod.GET)
	public ModelAndView buscarForm(@PathVariable("nome") String nome){
		ModelAndView model = new ModelAndView("/views/aluno/detalhes");
		Aluno aluno = this.alunoService.buscarPorNome(nome);
		model.addObject("aluno", aluno);
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(aluno);
		model.addObject("alunos", alunos);
		return model;
	}

	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public ModelAndView buscarPorNome(@Valid Aluno aluno){
		ModelAndView modelAndView = new ModelAndView("redirect:" +aluno.getNome());
		return modelAndView;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ModelAndView listar (){
		ModelAndView modelAndView = new ModelAndView("/views/aluno/listar");
		
		modelAndView.addObject("alunos", this.alunoService.listar());
			
		return modelAndView;
	}
	

	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public ModelAndView remover(@PathVariable("id") Long idAluno){
		this.alunoService.remover(idAluno);
		
		ModelAndView modelAndView = new ModelAndView("/views/aluno/listar");
		modelAndView.addObject("alunos", this.alunoService.listar());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editarForm (@PathVariable("id") Long idAluno){
		
		Aluno aluno = alunoService.buscarPorId(idAluno);
		ModelAndView modelAndView = new ModelAndView("/views/aluno/editar");
		
		if(aluno == null){
			return null;
		}
		
		modelAndView.addObject("aluno", aluno);
		modelAndView.addObject("acao", "EDITAR");
		return modelAndView ;
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public ModelAndView editar(@Valid Aluno aluno){
		ModelAndView modelAndView = new ModelAndView("redirect:listar");
		this.alunoService.editar(aluno);
		modelAndView.addObject("alunos", this.alunoService.listar());
		return modelAndView;
	}
	
	
}

package br.ufc.conbo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.model.Aluno;
import br.ufc.conbo.model.Frequencia;
import br.ufc.conbo.model.Participacao;
import br.ufc.conbo.service.AlunoService;
import br.ufc.conbo.service.FrequenciaService;
import br.ufc.conbo.service.ParticipacaoService;
import br.ufc.conbo.service.StorageService;
import br.ufc.conbo.service.impl.ParticipacaoServiceImpl;

@Controller
@RequestMapping("frequencia")
public class FrequenciaController {
	
	@Inject
	private FrequenciaService frequenciaService;
	
	@Inject
	private AlunoService alunoService;
	
	@Inject
	private ParticipacaoService participacaoService;

	@Inject
	private StorageService storageService;
		
	@RequestMapping(value = "/adicionar/{idAluno}", method = RequestMethod.GET)
	public ModelAndView adicionarForm(@PathVariable ("idAluno") Long idAluno){
		ModelAndView model = new ModelAndView("/views/aluno/frequencia");
		Aluno aluno = this.alunoService.buscarPorId(idAluno);
		model.addObject("aluno", aluno);
		model.addObject("frequencia", new Frequencia());
		return model;
	}
	
	@RequestMapping(value = "/adicionar", method = RequestMethod.POST)
	public ModelAndView adicionar(@ModelAttribute ("frequencia") Frequencia frequencia, 
			@RequestParam(value="file", required=true) MultipartFile file){
		
		frequencia.setDataEnvio(new Date());
		if (saveFile(file, frequencia)) {
			//Salvando o arquivo
			SimpleDateFormat dataFormat = new SimpleDateFormat("M");
			Date d = frequencia.getMes();
			dataFormat.format(d);
			frequencia.setMes(d);
			this.frequenciaService.salvar(frequencia);
		}
		//redirecionando para a lista de alunos
		ModelAndView model = new ModelAndView("/views/aluno/listar");
		model.addObject("alunos", this.alunoService.listar());
		return model;
}
	
	
	private boolean saveFile(MultipartFile file, Frequencia frequencia) {
		String nomeDoArquivo = new StringBuilder("CONBO-").append(frequencia.getParticipacao().getAluno().getNome()).toString();		
		try {
			//Alterando o caminho do arquivo
			frequencia.setCaminhoArquivo(storageService.store(file, nomeDoArquivo));
			frequencia.setNome(nomeDoArquivo);
			
			
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	@RequestMapping(value="/listar/{idAluno}", method = RequestMethod.GET)
	public ModelAndView listar(@PathVariable ("idAluno") Long idAluno){
		ModelAndView model = new ModelAndView("/views/aluno/listar_frequencias");
		Aluno a = this.alunoService.buscarPorId(idAluno);
		model.addObject("participacoes", a.getParticipacoes());
		return model;
	}
	
	
	
}

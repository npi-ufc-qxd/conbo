package br.ufc.conbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.conbo.util.alert.AlertSet;

@Controller
public class ConboController {
		
	@RequestMapping(value = "/")
	public ModelAndView cadastrarForm(){
		return new ModelAndView("views/index").addObject(
				"alertas", 
				new AlertSet()
					.withLongInfo("Exemplo mensagem de informação!")
					.withShortSuccess("Exemplo de mensagem de sucesso!")
					.withShortWarning("Exemplo de mensagem de aviso!")
					.withLongError("Exemplo de mensagem de erro!")
		);
	}
	
}

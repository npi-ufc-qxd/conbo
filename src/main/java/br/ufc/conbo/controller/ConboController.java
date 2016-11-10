package br.ufc.conbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConboController {
		
	@RequestMapping(value = "/")
	public String cadastrarForm(){
		return "index";
	}
	
}

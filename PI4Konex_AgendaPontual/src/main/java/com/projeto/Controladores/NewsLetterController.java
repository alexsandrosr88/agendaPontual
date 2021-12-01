package com.projeto.Controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.Entidades.NewsLetter;
import com.projeto.Servicos.NewsLatterServico;

@Controller
public class NewsLetterController {

	@Autowired
	private NewsLatterServico newsServ;
	
	@PostMapping("/newsletter/cadastro/home")
	public String cadastroEmail(@Valid @ModelAttribute("news") NewsLetter news, BindingResult result) {
	
		if (result.hasErrors()) {
			return "/home/home";
		}
		newsServ.cadastro(news);
		return "redirect:/";
	}
	
	@PostMapping("/newsletter/cadastro/medico/busca")
	public String cadastroEmail2(@Valid @ModelAttribute("news") NewsLetter news, BindingResult result) {
	
		if (result.hasErrors()) {
			return "resulBusca";
		}
		newsServ.cadastro(news);
		return "redirect:/medico/busca";
	}
}

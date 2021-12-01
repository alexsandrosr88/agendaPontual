package com.projeto.Controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.Entidades.Contato;
import com.projeto.Repositorios.ContatoRepository;
import com.projeto.Servicos.ContatoServico;

@Controller
public class ContatoController {
	//importar o repositorio
	@Autowired
	private ContatoRepository cr;
	
	@Autowired
	private ContatoServico conServ;
	
	//abrir a tela 
	@GetMapping("/sobre-nos")
	public ModelAndView abertura(Contato contato) {
		ModelAndView mv = new ModelAndView("/home/Sobre");
		return mv;
	}
	
	//enviar contato
	@PostMapping("/registrar")
	public ModelAndView registraContato(
			@Valid
			@ModelAttribute Contato contato, BindingResult verifica, RedirectAttributes redirectAtt) {
		
		//model
		ModelAndView mv = new ModelAndView("");
		
		//atributos
		boolean temErro= false;
		
		//VERIFICA SE TEM ERRO
		if(verifica.hasErrors() || verifica.hasFieldErrors()) {
			
			temErro = true;
			//referencia
			mv = new ModelAndView("/home/Sobre");
			mv.addObject("temErro",temErro);
			
		}else {
			//propriedade do redirect
			redirectAtt.addFlashAttribute("contato", contato);
			
			//salva no bda
			cr.save(contato);
			mv = new ModelAndView("redirect:/sobre-nos");
		}
		return mv;
	}
	
	@PostMapping("/resgistrarContatoHome")
	public String registraContatoHome(@Valid @ModelAttribute("contato") Contato contato,
			BindingResult verifica, RedirectAttributes redirectAtt) {
		
		if(verifica.hasErrors()) {
			redirectAtt.addFlashAttribute("contato", contato);
			return ("redirect:/");
			
		}else {
			conServ.cadastro(contato);
			return ("redirect:/");
		}
	}
}

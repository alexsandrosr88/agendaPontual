package com.projeto.Controladores;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public ModelAndView log() {
		ModelAndView mv = new ModelAndView("/home/tela_login");
		return mv;
	}
	
	@PostMapping("/log")
	public ModelAndView logAcess(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		boolean erro = false;
		
		if("wilson.santos@medico.com".equals(email)) {
			if("123456".equals(senha)) {
				mv = new ModelAndView("redirect:/medInd/painelMedInd");
			}
			
		}else if("raimunda.almada@outlook.com".equals(email)) {
			if("123456".equals(senha)) {
				mv = new ModelAndView("redirect:/recepcionista/painelRecep");
			}
		}else if("tatiane.cardoso@bol.com.br".equals(email)) {
			//paciente
			if("123456".equals(senha)) {
				mv = new ModelAndView("redirect:/paciente/dashboard");
			}
		}else {
			erro = true;
			mv.addObject("Por favor, revise os campos", erro);
		}
		return mv;
	}
}
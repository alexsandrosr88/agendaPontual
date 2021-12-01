package com.projeto.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AjudaController {
	
	@PostMapping("/ajuda/home")
	public String ajudaSite() {
		
		
		return ("home");
	}
}

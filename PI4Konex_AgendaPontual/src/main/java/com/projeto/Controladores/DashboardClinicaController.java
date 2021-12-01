package com.projeto.Controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.Entidades.Agenda;
import com.projeto.Entidades.Ajuda;
import com.projeto.Entidades.Clinica;
import com.projeto.Entidades.Feedback;
import com.projeto.Entidades.Medico;
import com.projeto.Entidades.Pagamento;
import com.projeto.Entidades.Recepcionista;
import com.projeto.Repositorios.MedicojpaRepository;



@Controller
public class DashboardClinicaController {
	//chamada do repositorio
	@Autowired
	private MedicojpaRepository medR;
	
	@GetMapping("/viewDash")
	public ModelAndView viewCli(Medico medico) {
		ModelAndView mv = new ModelAndView("/clinica/DashboardClinica");
		
		//referencio o que eu quero listar, no caso uma lista.
		List<Medico> bestMed = medR.melhoresMedCli(2);
				
		//seto o objeto a ser exibido atraves do thymeleaf
		mv.addObject("bestMed", bestMed);
		
		return mv;
	}
	
	@GetMapping("/NovoMed")
	public ModelAndView createMedico(Medico medico){
		ModelAndView mv = new ModelAndView("/clinica/CadastroMed");
		return mv;
	}
	
	@GetMapping("/NovoRecep")
	public ModelAndView createRecep(Recepcionista recep){
		ModelAndView mv = new ModelAndView("/clinica/CadastroRecep");
		return mv;
	}

	@GetMapping("/ConsultaPagamentos")
	public ModelAndView listPag(Pagamento pagamentos){
		ModelAndView mv = new ModelAndView("/clinica/ConsultarPagamentos");
		return mv;
	}

	@GetMapping("/HistoricoAgenda")
	public ModelAndView listaHistorico(Agenda agenda){
		ModelAndView mv = new ModelAndView("/clinica/ConsultarAgendamentos");
		return mv;
	}

	@GetMapping("/FeedbackClinica")
	public ModelAndView listaFeedbacks(Feedback feedbacks){
		ModelAndView mv = new ModelAndView("/clinica/ConsultarFeedback");
		return mv;
	}
	
	@GetMapping("/ConfigureCli")
	public ModelAndView configuraCli(Clinica clinica){
		ModelAndView mv = new ModelAndView("/clinica/ConfigureClinica");
		return mv;
	}

	@GetMapping("/SairCli")
	public ModelAndView sair() {
		ModelAndView mv = new ModelAndView("/logoffCli");
		return mv;
	}
	
	@GetMapping("/AjudaClinica")
	public ModelAndView listaFeedbacks(Ajuda ajuda){
		ModelAndView mv = new ModelAndView("/clinica/Ajuda");//falta implementar
		return mv;
	}
	/*
	@PostMapping("/createAjuda")
	public ModelAndView createAjuda(@ModelAttribute Ajuda ajuda){
		ModelAndView mv = new ModelAndView("");
		return mv;
	}*/

}
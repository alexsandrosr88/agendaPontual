package com.projeto.Controladores;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.Entidades.Medico;
import com.projeto.Entidades.Planos;
import com.projeto.Repositorios.MedicojpaRepository;
import com.projeto.Servicos.PlanosService;


@Controller
public class PlanosController {
	//Chamada dos Repository/Servicos
	
	@Autowired
	private PlanosService plR;
	
	@Autowired
	private MedicojpaRepository mdR;

	// abrir a tela
	@GetMapping("/planos")
	public String abertura(Planos planos, @ModelAttribute Medico medico) {
		return "/planos/planos";
	}
	
	//Aquisição do plano 1
	@PostMapping("/personalSelected")
	public ModelAndView createPersonal(@ModelAttribute Planos planos, @ModelAttribute Medico medico) {
		ModelAndView mv = new ModelAndView("/planos/PosPlanos");
		float valorAnual = (99.99f * 12f) - 100f;
		DecimalFormat df = new DecimalFormat("#.##");
		planos.setValorAnual(df.format(valorAnual));
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		return mv;
	}
	
	//Aquisição do plano 2
	@PostMapping("/economySelected")
	public ModelAndView createEconomy(@ModelAttribute Planos planos, @ModelAttribute Medico medico) {
		ModelAndView mv = new ModelAndView("/planos/PosPlanos");
		
		//calculando valor anual
		float valorAnual = (279.80f * 12f) - 100f;
		DecimalFormat df = new DecimalFormat("#.##");
		planos.setValorAnual(df.format(valorAnual));
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		return mv;
	}
	
	//Aquisição do plano 3
	@PostMapping("/premiumSelected")
	public ModelAndView createPremium(@ModelAttribute Planos planos, @ModelAttribute Medico medico) {
		ModelAndView mv = new ModelAndView("/planos/PosPlanos");
		
		//calculando valor anual
		float valorAnual = (679.80f * 12f) - 100f;
		DecimalFormat df = new DecimalFormat("#.##");
		planos.setValorAnual(df.format(valorAnual));
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		return mv;
	}
	
	@PostMapping("/personalize")
	public ModelAndView createPersonalize(
		@Valid 
		@ModelAttribute Planos planos, 
		@ModelAttribute Medico medico, BindingResult verifica) {
		
		//Model
		ModelAndView mv = new ModelAndView("/planos/Planos");
		
		//Var
		String nome;
		int qtdRecep;
		int qtdMed;
		int qtdTol;
		float valor;
		DecimalFormat df = new DecimalFormat("#.##");
		
		nome = "Personalizado";
		qtdRecep = planos.getQntRec();
		qtdMed = planos.getQntMed();
		qtdTol = planos.getQntTolerancia();
		valor = planos.getValorPlano();
		float valorAnual = 0f;
		String aux = null;
		
		if(verifica.hasErrors()) {
			mv = new ModelAndView("/planos/planos");
			System.out.println("tem errooooo*****");
			
		}else {
			
			//verificando os valores preenchidos
			if(qtdRecep == 1 && qtdMed == 1 && qtdTol == 3){
				valor = 99.99f;
				
			}else if(qtdRecep == 3 && qtdMed == 5 && qtdTol == 5){
				valor = 279.80f;
				
			}else if(qtdRecep == 5 && qtdMed == 15 && qtdTol == 7){
				valor = 679.80f;
				
			}else{
				//calculando valor o total
				valor = ((qtdRecep * 7 ) + (qtdMed * 10) + ( qtdTol * 20));
				
				//calculando o valor anual
				valorAnual = (valor * 12f) - 100f;
			}
			
			aux = df.format(valorAnual);
			//resolve o bug
			mv.addObject("nomePl", nome);
			mv.addObject("valor", valor);
			mv.addObject("recep", qtdRecep);
			mv.addObject("med", qtdMed);
			mv.addObject("tole", qtdTol);
			mv.addObject("valorAnual", aux);
		}
		
		planos = new Planos(nome, valor, qtdRecep, qtdMed, qtdTol, aux);
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		return mv;
	}
	
	//Aquisição do plano 
	@PostMapping("/personalizedSelected")
	public ModelAndView createPersonalized(@ModelAttribute Planos planos, @ModelAttribute Medico medico) {
		ModelAndView mv = new ModelAndView("/planos/PosPlanos");
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		return mv;
	}
	
	//Aplicação do desconto
	@PostMapping("/desconto")
	public ModelAndView createDesconto(@ModelAttribute Planos planos, @ModelAttribute Medico medico, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/planos/PosPlanos");
		
		String codigo = request.getParameter("cupomDesc");
		float valorAux;
		float valorFinal;
		boolean temErro = false;
		float valorAnual = 0f;
		
		//condição
		if("10PONTUAL".equalsIgnoreCase(codigo)) {
			valorAux = (planos.getValorPlano() * 10)/100;
			valorFinal = planos.getValorPlano() - valorAux;
			planos.setValorPlano(valorFinal);
			
			//calculando valor anual
			valorAnual = (valorFinal * 12f) - 100f;
			DecimalFormat df = new DecimalFormat("#.##");
			planos.setValorAnual(df.format(valorAnual));
		}else{
			temErro = true;
		}
		
		mv.addObject("planos", planos);
		mv.addObject("medico", medico);
		mv.addObject("temErro", temErro);
		
		return mv;
	}
	
	@PostMapping("/aquisitionPlanos")
	public ModelAndView saveAquisition(
		@ModelAttribute Planos planos, 
		@Valid
		@ModelAttribute("medico") Medico med, BindingResult verifica, HttpServletRequest request) {
		
		//atribuições
		ModelAndView mv = new ModelAndView("");
		
		
		if(verifica.hasErrors()) {
			mv = new ModelAndView("/planos/PosPlanos");
			System.out.println("tem errooooo*****");
		}else {
			plR.createPlanos(planos);
			mdR.save(med);
			mv = new ModelAndView("/planos/Confirma");
		}
		return mv;
	}
}
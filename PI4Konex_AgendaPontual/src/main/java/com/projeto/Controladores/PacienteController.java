package com.projeto.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.Dto.AvaliacoesNegativasDTO;
import com.projeto.Dto.AvaliacoesPositivasDTO;
import com.projeto.Dto.ConsultasPacienteDTO;
import com.projeto.Dto.QuantidadeAtendimentosDTO;
import com.projeto.Dto.UltimoIdDTO;
import com.projeto.Entidades.Ajuda;
import com.projeto.Entidades.Consulta;
import com.projeto.Entidades.Feedback;
import com.projeto.Entidades.Medico;
import com.projeto.Entidades.NewsLetter;
import com.projeto.Entidades.Paciente;
import com.projeto.Entidades.Usuario;
import com.projeto.Repositorios.ConsultaRepositorio2;
import com.projeto.Servicos.AjudaServico;
import com.projeto.Servicos.CidadeServico;
import com.projeto.Servicos.ConsultaServico;
import com.projeto.Servicos.ConvenioServico;
import com.projeto.Servicos.EspecialidadeServico;
import com.projeto.Servicos.FeedbackServico;
import com.projeto.Servicos.MedicoServico;
import com.projeto.Servicos.PacienteServico;
import com.projeto.Servicos.UltimoIdDTOServico;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private MedicoServico medServ;

	@Autowired
	private EspecialidadeServico espServ;

	@Autowired
	private CidadeServico cidServ;

	@Autowired
	private PacienteServico paciServ;

	@Autowired
	private AjudaServico ajuServ;

	@Autowired
	private ConsultaServico consServ;

	@Autowired
	private FeedbackServico feeServ;

	@Autowired
	private ConsultaRepositorio2 cr2;

	@Autowired
	private ConvenioServico convServ;

	@Autowired
	private UltimoIdDTOServico ultServ;

	@GetMapping("/dashboard")
	public String dashboardPaci(Model model) {
		Integer idUsu = 1;
		// paciServ.pesquisaPacientePorUsuarioId(idUsu)
		model.addAttribute("resumo", consServ.consultasMarcadas(idUsu));

		return ("/paciente/dashboardPaci");
	}

	@GetMapping("/medico/busca")
	public ModelAndView novaConsulta(@RequestParam(required = false) Integer cidade,
			@RequestParam(required = false) String esp, @RequestParam(required = false) Integer bairro,
			@RequestParam(required = false) String espec, @RequestParam(required = false) String sexMas,
			@RequestParam(required = false) String sexFem, @RequestParam(required = false) Float valorMin,
			@RequestParam(required = false) Float valorMax, @RequestParam(required = false) Integer minExp,
			@RequestParam(required = false) Integer maxExp) {

		ModelAndView mv = new ModelAndView("/paciente/novaConsulta");

		mv.addObject("cidades", cidServ.findAll());
		mv.addObject("medicos",
				medServ.buscaMedCompleta(cidade, bairro, espec, sexMas, sexFem, valorMin, valorMax, minExp, maxExp));
		mv.addObject("especs", espServ.findAll());
		mv.addObject("news", new NewsLetter());
		return mv;
	}

	@PostMapping("/cadastro/validacao/{id}")
	public String pacienteCadastro(@PathVariable("id") Integer id, @ModelAttribute("paciente") Paciente nwpaciente) {
		Consulta consulta = consServ.buscaConsultaPorId(id);
		Paciente novoPaciente = paciServ.cadastro(nwpaciente);
		consulta.setPaciente(novoPaciente);
		consServ.cadastro(consulta);
		return ("redirect:/paciente/confirmacao");
	}

	@GetMapping("/confirmacao")
	public String confirmacao() {
		return "/paciente/tela_confirmShop";
	}
	
//	@GetMapping("/consultas/index")
//	public String consultasIndex(Model model) {
//		model.addAttribute("lista", consServ.consultasMarcadas(1));
//		model.addAttribute("con", new Consulta());
//		return "/paciente/consultasIndex";
//	}
	
	@GetMapping("/consultas")
	public String consultasIndex(Model model) {
		ConsultasPacienteDTO consulta;
		
		if(consServ.consultaPorPaciente(1).isEmpty()) {
			consulta = new ConsultasPacienteDTO();
			model.addAttribute("lista", consulta);
			model.addAttribute("positiva", new AvaliacoesPositivasDTO());
			model.addAttribute("negativa", new AvaliacoesNegativasDTO());
			model.addAttribute("total", new QuantidadeAtendimentosDTO());
			model.addAttribute("con", consulta);
			
		}else {	
			consulta = consServ.consultaPorPaciente(1).get(0);
		model.addAttribute("lista", consServ.consultaPorPaciente(1));
		model.addAttribute("positiva", feeServ.buscaPositiva(consulta.getIdMed()));
		model.addAttribute("negativa", feeServ.buscaNegativa(consulta.getIdMed()));
		model.addAttribute("total", medServ.buscaQteAtendimento(consulta.getIdMed()));
		model.addAttribute("con", consulta);
		}
		return "/paciente/consultas";
	}

//	@GetMapping("/consultas/index")
//	public String consultas(Model model,
//			@ModelAttribute("con") ConsultasPacienteDTO consulta,
//			@ModelAttribute("positiva") AvaliacoesPositivasDTO positiva,
//			@ModelAttribute("negativa") AvaliacoesNegativasDTO negativa,
//			@ModelAttribute("total") QuantidadeAtendimentosDTO qteAtendimento) {
//		model.addAttribute("lista", consServ.consultaPorPaciente(11));
//		return "/paciente/consultas";
//	}
	
//	@GetMapping("/consulta/{idCons}")
//	public String consultaDetalhe(@PathVariable("idCons") Integer idCons, Model model, RedirectAttributes redirect) {
//		 Consulta consulta = consServ.consultaPorIdCons(idCons);
//		model.addAttribute("lista", consServ.consultaPorPaciente(11));
//		redirect.addFlashAttribute("positiva", feeServ.buscaPositiva(consulta.getIdMed()));
//		redirect.addFlashAttribute("negativa", feeServ.buscaNegativa(consulta.getIdMed()));
//		redirect.addFlashAttribute("total", medServ.buscaQteAtendimento(consulta.getIdMed()));
//		redirect.addFlashAttribute("con", consulta);
//		return ("redirect:/paciente/consultas#painelDinamico");
//	}
	
	@GetMapping("/consulta/{idCons}")
	public String consultaDetalhe(@PathVariable("idCons") Integer idCons, Model model, RedirectAttributes redirect) {
		ConsultasPacienteDTO consulta = consServ.consultaPorIdCons(idCons);
		model.addAttribute("lista", consServ.consultaPorPaciente(1));
		model.addAttribute("positiva", feeServ.buscaPositiva(consulta.getIdMed()));
		model.addAttribute("negativa", feeServ.buscaNegativa(consulta.getIdMed()));
		model.addAttribute("total", medServ.buscaQteAtendimento(consulta.getIdMed()));
		model.addAttribute("con", consulta);
		return ("/paciente/consultas");
	}

	@GetMapping("/medico/agenda")
	public ModelAndView agendaMedica(@RequestParam Integer idMed, @ModelAttribute("dados") Consulta dados) {
		ModelAndView mv = new ModelAndView("/paciente/tela_resumo");
		mv.addObject("medico", medServ.medicoResumo(idMed));
		mv.addObject("paciente", new Paciente());
		mv.addObject("feedback", feeServ.buscaFeedbackPorMedico(idMed));
		mv.addObject("positiva", feeServ.buscaPositiva(idMed));
		mv.addObject("negativa", feeServ.buscaNegativa(idMed));
		mv.addObject("total", medServ.buscaQteAtendimento(idMed));
		mv.addObject("seg", medServ.buscaSeg(idMed));
		mv.addObject("ter", medServ.buscaTer(idMed));
		mv.addObject("qua", medServ.buscaQua(idMed));
		mv.addObject("qui", medServ.buscaQui(idMed));
		mv.addObject("sex", medServ.buscaSex(idMed));
		mv.addObject("sab", medServ.buscaSab(idMed));
		return mv;
	}

	@PostMapping("/consulta/validacao/{idMed}")
	public ModelAndView valida(@PathVariable("idMed") Integer idMed, @ModelAttribute("dados") Consulta dados) {
		Integer idUsu = 21;
		ModelAndView mv = new ModelAndView("/paciente/tela_validation");
		Medico med = new Medico();
		mv.addObject("medico", medServ.medicoResumo(idMed));
		mv.addObject("paciente", new Paciente());
		mv.addObject("feedback", feeServ.buscaFeedbackPorMedico(idMed));
		mv.addObject("positiva", feeServ.buscaPositiva(idMed));
		mv.addObject("negativa", feeServ.buscaNegativa(idMed));
		mv.addObject("total", medServ.buscaQteAtendimento(idMed));
		mv.addObject("convenios", convServ.listaConvenio());
		med.setIdMed(idMed);
		dados.setMedico(med);
		consServ.cadastro(dados);
		mv.addObject("numero", ultServ.buscaUtimoId());
		mv.addObject("detalhes", consServ.buscaConsultaPorId(ultServ.buscaUtimoId().getNum()));
		return mv;
	}
	
	@PostMapping("/consulta/confirmacao/{id}")
	public String consultaCofirmacao(@PathVariable("id") Integer idCons, @ModelAttribute("paciente") Paciente paciente) {
		Integer idUsu = 21;
		paciServ.atualizaPacienteConsulta(idCons, idUsu, paciente);
		return ("redirect:/paciente/dashboard");
	}

	@PostMapping("/consulta/cancela/{id}")
	public String cancelaConsulta(@PathVariable("id") Integer idCons) {
		consServ.excluiConsulta(idCons);
		return ("redirect:/paciente/consultas");
	}
	
	@PostMapping("/consulta/altera")
	public String alteraConsulta(@ModelAttribute Consulta altera) {	
		consServ.alteraConsulta(altera);
		return "redirect:/paciente/consultas";
	}

	@GetMapping("/radarPontual")
	public String radarPontual() {
		return "/paciente/tela_radarPontual";
	}

	// Deixei RequestParam para desenrolar o código sem security. Mas o correto é
	// usar o PathVariable.
	@GetMapping("/feedback")
	public String feedback(@RequestParam(required = false) Integer idUsu, Model model) {
		idUsu = 9;
		Paciente paciente = paciServ.pesquisaPacientePorUsuarioId(idUsu);
		model.addAttribute("feedback", new Feedback());
		model.addAttribute("num", new UltimoIdDTO());
		model.addAttribute("consulta", cr2.pesquisaConsultaSemFeed(paciente.getIdPaci()));
		return "/paciente/tela_frame8";
	}

	@PostMapping("/feedback/cadastro")
	public String feedbackCadastro(@RequestParam Integer idCons, @ModelAttribute("feedback") Feedback feedback) {
		feeServ.feedbackCadastro(idCons, feedback);
		return ("redirect:/paciente/feedback");
	}

	// Deixei RequestParam para desenrolar o código sem security. Mas o correto é
	// usar o PathVariable.
	@GetMapping("/configuracoes")
	public String configurações(@RequestParam(required = false) Integer idUsu, Model model) {
		idUsu = 11;
		model.addAttribute("paciente", paciServ.pesquisaPacientePorUsuarioId(idUsu));
		return "/paciente/tela_configuracoes";
	}

	@PostMapping("/configuracoes/atualiza")
	public String configuracoesSave(@ModelAttribute("paciente") Paciente paciente) {
		paciServ.cadastroVoid(paciente);
		return ("redirect:/paciente/configuracoes");
	}

	@RequestMapping("/ajuda")
	public String medIndAjuda(Model model) {
		model.addAttribute("aju", new Ajuda());
		return "/paciente/tela_ajuda";
	}

	@PostMapping("/ajuda/nova")
	public String criaAjudaMedInd(@ModelAttribute("ajuda") Ajuda ajuda) {
		Usuario usu = new Usuario();
		usu.setIdUsu(2);
		ajuda.setUsuario(usu);
		ajuServ.criaAjuda(ajuda);
		return ("redirect:/paciente/dashboard");
	}

	@GetMapping("/saida")
	public String saida() {
		return "/paciente/tela_saida";
	}
}

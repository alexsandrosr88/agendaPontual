package com.projeto.Controladores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projeto.Entidades.Agenda;
import com.projeto.Entidades.Ajuda;
import com.projeto.Entidades.Consulta;
import com.projeto.Entidades.DiasSemana;
import com.projeto.Entidades.Especialidade;
import com.projeto.Entidades.Feedback;
import com.projeto.Entidades.Horario;
import com.projeto.Entidades.Medico;
import com.projeto.Entidades.ProjecaoSalarial;
import com.projeto.Entidades.Recepcionista;
import com.projeto.Entidades.Usuario;
import com.projeto.Repositorios.AgendaRepositorio;
import com.projeto.Repositorios.ConsultaRepositorio;
import com.projeto.Repositorios.EspecialidadeRepositorio;
import com.projeto.Repositorios.FeedbackRepositorio2;
import com.projeto.Repositorios.HorarioRepositorio;
import com.projeto.Repositorios.MedicoRepositorio;
import com.projeto.Repositorios.MedicojpaRepository;
import com.projeto.Repositorios.ProjecaoRepositorio;
import com.projeto.Servicos.AjudaServico;
import com.projeto.Servicos.DoencaServico;
import com.projeto.Servicos.EspecialidadeServico;
import com.projeto.Servicos.FeedbackServico;
import com.projeto.Servicos.MedicoServico;
import com.projeto.Servicos.PagamentoServico;
import com.projeto.Servicos.RecepcionistaServico;
import com.projeto.Servicos.UsuarioServico;

@Controller
@RequestMapping(value = "/medInd")
public class MedicoIndController {

    @Autowired
    EspecialidadeServico espServ;
    @Autowired
    AjudaServico ajuServ;
    @Autowired
    UsuarioServico usuServ;
    @Autowired
    MedicoServico medServ;
    @Autowired
    MedicojpaRepository medJpaRepo;
    @Autowired
    DoencaServico doeServ;
    @Autowired
    FeedbackServico feedServ;
    @Autowired
    RecepcionistaServico recepServ;
    @Autowired
    PagamentoServico pagServ;
    @Autowired
    ProjecaoRepositorio projRepo;
    @Autowired
    FeedbackRepositorio2 feedRepo;
    @Autowired
    ConsultaRepositorio consRepo;
    @Autowired
    EspecialidadeRepositorio espRepo;
    @Autowired
    MedicoRepositorio medRepo;
    @Autowired
    AgendaRepositorio agenRepo;
    @Autowired
    HorarioRepositorio horaRepo;
    @Autowired


    //Por hora serão metodos sem uma logica ou despachamento de tela
    @RequestMapping("/primeiroPassoMedInd")
    public String primeiroPasso(){

        return "/medInd/primPt1Med";
    }

    @RequestMapping("/segundoPassoMedInd")
    public String segundoPasso(Model model){
        Medico medInd = new Medico();
        List<Especialidade> listaEsp = espServ.findAll();
        model.addAttribute("medInd", medInd);
        model.addAttribute("listaEsp", listaEsp);


        return "/medInd/primPt2MedInd";
    }

    @RequestMapping("/cadPt1")
    public String cadPt1(@ModelAttribute("medInd") Medico medInd, Model model){

        return "/medInd/primPt2Med";
    }

    @RequestMapping("/terceiroPassoMedInd")
    public String terceiroPasso(Model model){
        return "/medInd/primPt2Med";
    }

    @RequestMapping("/cadPt2")
    public String cadPt2(@ModelAttribute("medInd")Medico medInd, Model model){
        
        return "/medInd/priMedClin";
    }

    @RequestMapping("/quartoPassoMedInd")
    public String quartoPasso(@ModelAttribute("medInd") Medico medInd,Model model){
    	ProjecaoSalarial projSal = new ProjecaoSalarial();
        Agenda agenda = new Agenda();
        model.addAttribute("doeResumo", doeServ.buscaDoencaPorMedico(2));
    	model.addAttribute("proj", projSal);
        model.addAttribute("agenda", agenda);

        return "/medInd/priMedClin";
    }
    
    @RequestMapping("/salvaProj")
    public String salvaProj(@ModelAttribute("medInd") Medico medInd,
    @ModelAttribute("proj")ProjecaoSalarial projSal, @ModelAttribute("agenda") Agenda agenda,
    HttpServletRequest request, Model model) throws ParseException {
        medInd.setIdMed(2);
        projSal.setMedico(medInd);
        // Atributos web
        String time1 = projSal.getHoraInicio().toString();
        String time2 = projSal.getHoraSaida().toString();
        String timeInter = projSal.getHoraIntervalo().toString();
        float meta = projSal.getMetaPaci();
        float valorPorPaci = projSal.getValorPaci();
        int qtdDiasTrab = projSal.getQtdDias();
        String opSegure = request.getParameter("tempoSeguranca");
        String seg = request.getParameter("seg");
        String ter = request.getParameter("ter");
        String qua = request.getParameter("qua");
        String qui = request.getParameter("qui");
        String sex = request.getParameter("sex");
        String sab = request.getParameter("sab");
        

        // atributos auxiliares
        int qtdPaciDia = 0;
        int minBase = 0;
        float qtdPaciMes = 0f;
        int intervalo = 60;

        // calculando a diferença
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        LocalTime date1Hora = LocalTime.parse(time1);
        LocalTime date2Hora = LocalTime.parse(time2);
        LocalTime intervaloHora = LocalTime.parse(timeInter); 
        Date date1 = format.parse(time1);
        Date date2 = format.parse(time2);
        long difference = date2.getTime() - date1.getTime();

        // horario Base do calculo qtd de minutros trabalhos
        long diffMinutos = difference / (60 * 1000);
        minBase = (int) diffMinutos;

        // Base menos o intervalo
        minBase = minBase - intervalo;

        // Verifica se ele deseja tempo de segunraça
        if ("Sim".equalsIgnoreCase(opSegure)) {
            minBase = minBase - 20;
        }

        // Calcular a qtd de paciente que ele deve atender no mes
        qtdPaciMes = meta / valorPorPaci;
        qtdPaciMes = Math.round(qtdPaciMes);

        // Calcula a qtd de paciente no dia
        qtdPaciDia = 30 / qtdDiasTrab;
        qtdPaciDia = Math.round(qtdPaciDia);

        // Gerar a duração da consulta
        // dividir o minBase pela qtdPaciente do dia
        int durationMin = (minBase / qtdPaciDia) - 5;
        // qtdPaciDia = Math.round(durationMin);
        // Consulta 190. sendo que era 119
        int totalCons = Math.round(qtdPaciMes);
        // setar os valores
        projSal.setTempoDuracao(durationMin);
        projSal.setQtdPaciDias(qtdPaciDia);
        projSal.setTotalConsulta(totalCons);
        
        //Logica do Calendario e Agenda do Médico
        int aux = durationMin;
        while(aux<=minBase) {
            Agenda agen = new Agenda();
            Horario hora = new Horario();
            DiasSemana diaSemana = new DiasSemana();
        	aux+=durationMin;
        	date1Hora = date1Hora.plusMinutes(durationMin);
            boolean verifica = false;
            if(seg!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(2);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }
             if(ter!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(3);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }
            if(qua!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(4);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }
            if(qui!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(5);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }
            if(sex!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(6);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }
            if(sab!=null){
                hora.setHorario(date1Hora);
                horaRepo.save(hora);
                diaSemana.setIdDia(7);
                agen.setDia(diaSemana);
                agen.setHora(hora);
                agen.setMedico(medInd);
                agenRepo.save(agen);
            }else{
                verifica = true;
            }

            
        	if(date1Hora==intervaloHora) {
        		
                //Aumenta uma hora 
        		date1Hora=date1Hora.plusHours(1);
        		
        		if(date1Hora!=date2Hora) {
        			continue;
        		}
                   
        	}
        	
        }

        // salvar no banco
        model.addAttribute("doeResumo", doeServ.buscaDoencaPorMedico(2));
        model.addAttribute("proj", projSal);
        model.addAttribute("agendaMedSeg", medRepo.buscaSeg(2));
        model.addAttribute("agendaMedTer", medRepo.buscaTer(2));
        model.addAttribute("agendaMedQua", medRepo.buscaQua(2));
        model.addAttribute("agendaMedQui", medRepo.buscaQui(2));
        model.addAttribute("agendaMedSex", medRepo.buscaSex(2));
        model.addAttribute("agendaMedSab", medRepo.buscaSab(2));

        projRepo.save(projSal);
        return "/medInd/priMedClin";
    }


    @RequestMapping("/painelMedInd")
    public String painelMedInd(Model model){
        Integer id = 2;
        Medico pontos = medRepo.infoMed(id);
        List<ProjecaoSalarial> listaProj = projRepo.listaProj(id);
        model.addAttribute("pontos", pontos);
        model.addAttribute("listaProj", listaProj);

        return "/medInd/painelPinMedInd";
    }
/*
    @RequestMapping("/consoleMed")
    public String consoleMed(Model model){

        model.addAttribute("listaPaci", consRepo.consultaConsole(2));
        return ("/medInd/console");
    }*/
    
    @GetMapping("/consoleMed")
    public String consoleMed(){
        return ("/medInd/infoPaciMed");
    }
    @RequestMapping("/consoleMed2")
    public String consoleMed2(){
        return ("/medInd/consoleDuplicada");
    }

    @RequestMapping("/visualizaPaci/{idPaci}")
    public String visualizaPaci(@PathVariable Integer idPaci, Model model){
        Optional<Consulta> opCons = this.consRepo.findById(idPaci);
        if(opCons.isPresent()){
            model.addAttribute("dadosPaci", consRepo.consoleDetalhado(idPaci));
        }
        model.addAttribute("listaPaci", consRepo.consultaConsole(2));

        return "/medInd/console";
    }

    @RequestMapping("/consultarAgenda")
    public String consultarAgenda(Model model){
        
        model.addAttribute("listaCons", consRepo.listagemPainelMedInd(2));
        
        return "/medInd/consulAgenMedInd";
    }

    @RequestMapping("/projSalarial")
    public String projecaoSalarial(Model model){
        model.addAttribute("agendaMedSeg", medRepo.buscaSeg(2));
        model.addAttribute("agendaMedTer", medRepo.buscaTer(2));
        model.addAttribute("agendaMedQua", medRepo.buscaQua(2));
        model.addAttribute("agendaMedQui", medRepo.buscaQui(2));
        model.addAttribute("agendaMedSex", medRepo.buscaSex(2));
        model.addAttribute("agendaMedSab", medRepo.buscaSab(2));

        return "/medInd/priMedClin";
    }

    @RequestMapping("/pagamentos")
    public String medIndPagamentos(Model model){
        Integer idMed = 2;
        model.addAttribute("dadosPag", pagServ.listaPagPorMed(idMed));
        model.addAttribute("qtdTotal", pagServ.qtdTotal(idMed));
        model.addAttribute("qtdDin", pagServ.qtdDin(idMed));
        model.addAttribute("qtdCred", pagServ.qtdCre(idMed));
        model.addAttribute("qtdDeb", pagServ.qtdDeb(idMed));

        return "/medInd/consulPagClin";
    }


    @RequestMapping("/feedback")
    public String medIndFeedback(Model model){
        Integer idMed = 2;
        model.addAttribute("listaFeed", feedServ.listaFeedback(idMed));
        
        return "/medInd/feedBkMedInd";
    }

    @RequestMapping("/mostraFeed/{idFeed}")
    public String respondFeedback(@PathVariable Integer idFeed, Model model){
        Optional<Feedback> feedOp = this.feedRepo.findById(idFeed);
        if(feedOp.isPresent()){
            
            model.addAttribute("dadosFeed", feedServ.listaFeedPorID(idFeed));
        }


        return medIndFeedback(model);
    }

    @RequestMapping("/novoRecep")
    public String novoRecep(Model model){
        Usuario usu = new Usuario();
        Recepcionista recep = new Recepcionista();
        
        model.addAttribute("usu", usu);
        model.addAttribute("recep", recep);

        return "/medInd/novoRecepMedInd";
    }

    @RequestMapping("/criaRecep")
    public String criaRecep(@ModelAttribute("usu")Usuario usu, @ModelAttribute("recep")Recepcionista recep,Model model){
        usuServ.criaUsu(usu);
        recepServ.criaRecep(recep);
        return painelMedInd(model);
    }
    @RequestMapping("/medIndConfig")
    public String medIndConfig(Model model){
        Usuario usu = new Usuario();
        Medico med = new Medico();
        model.addAttribute("listaEsp", espRepo.findAll());
        model.addAttribute("dadosMed", medServ.infoMed(2));
        model.addAttribute("usu", usu);
        model.addAttribute("med", med);
        return "/medInd/configMedCli";
    }

    @RequestMapping("/atualiza")
    public String atualizaMedInd(@ModelAttribute("usu") Usuario usu, @ModelAttribute("med") Medico med, Model model){
        usu.setIdUsu(7);
        med.setIdMed(2);
        med.setUsuario(usu);

        usuServ.atualizaUsuario(usu);
        medServ.atualizaMedico(med);

        return painelMedInd(model);
    }

    @RequestMapping("/ajuda")
    public String medIndAjuda(Model model){
        model.addAttribute("aju", new Ajuda());
        return "/medInd/tela_ajudaMed";
    }
    
    @PostMapping("/criaAjudaMedInd")
    public String criaAjudaMedInd(@ModelAttribute("ajuda") Ajuda ajuda, Model model) {
    	Usuario usu = new Usuario();
    	usu.setIdUsu(2);
    	ajuda.setUsuario(usu);
    	ajuServ.criaAjuda(ajuda);
    	return painelMedInd(model);
    } 

    @RequestMapping("/telaSair")
    public String telaSair(){

        return "/medInd/tela_sairMed";
    }

}

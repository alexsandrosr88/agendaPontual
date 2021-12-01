package com.projeto.Controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.Entidades.Agenda;
import com.projeto.Entidades.Ajuda;
import com.projeto.Entidades.Cidade;
import com.projeto.Entidades.Consulta;
import com.projeto.Entidades.Convenio;
import com.projeto.Entidades.Endereco;
import com.projeto.Entidades.Especialidade;
import com.projeto.Entidades.Medico;
import com.projeto.Entidades.Paciente;
import com.projeto.Entidades.Pagamento;
import com.projeto.Entidades.Recepcionista;
import com.projeto.Entidades.Triagem;
import com.projeto.Entidades.Usuario;
import com.projeto.Repositorios.ConsultaRepositorio;
import com.projeto.Repositorios.EnderecoRepositorio;
import com.projeto.Repositorios.MedicojpaRepository;
import com.projeto.Repositorios.PacienteRepositorio;
import com.projeto.Repositorios.TriagemRepositorio;
import com.projeto.Repositorios.UsuarioRepositorio;
import com.projeto.Servicos.AjudaServico;
import com.projeto.Servicos.ClinicasServico;
import com.projeto.Servicos.ConsultaServico;
import com.projeto.Servicos.DoencaServico;
import com.projeto.Servicos.EspecialidadeServico;
import com.projeto.Servicos.FeedbackServico;
import com.projeto.Servicos.MedicoServico;
import com.projeto.Servicos.PagamentoServico;
import com.projeto.Servicos.RecepcionistaServico;
import com.projeto.Servicos.UsuarioServico;

@Controller
@RequestMapping(value = "/recepcionista")
public class RecepcionistaController {

    @Autowired
    RecepcionistaServico recepServ;
    @Autowired
    EspecialidadeServico espServ;
    @Autowired
    UsuarioServico usuServ;
    @Autowired
    UsuarioRepositorio repoUsu;
    @Autowired
    PagamentoServico pagServ;
    @Autowired
    AjudaServico ajuServ;
    @Autowired
    ConsultaServico consServ;
    @Autowired
    ConsultaRepositorio repoCons;
    @Autowired
    ClinicasServico cliServ;
    @Autowired
    MedicoServico medServ;
    @Autowired
    DoencaServico doeServ;
    @Autowired
    FeedbackServico feedServ;
    @Autowired
    TriagemRepositorio repoTri;
    @Autowired
    MedicojpaRepository medRepo;
    @Autowired
    PacienteRepositorio paciRepo;
    @Autowired
    EnderecoRepositorio endRepo;
    @Autowired
    UsuarioRepositorio usuRepo;

    


    @GetMapping("/painelRecep")
    private String painelRecep(Model model) {
        Paciente paci = new Paciente();
        Integer idCli = 1;
        List<Consulta> listagemPainel = consServ.listagemPainel(idCli);
        model.addAttribute("paci", paci);
        model.addAttribute("listagemPainel", listagemPainel);
        return "/recepcionista/tela_painelRecep";
    }

    @RequestMapping("/mostraPaci")
    private String paginaAddPaci(Model model) {
        Paciente paci = new Paciente();
        Usuario usu = new Usuario();
        Endereco end = new Endereco();
        Triagem tri = new Triagem();
        Medico med = medServ.infoMed(1);
        model.addAttribute("med", med);
        model.addAttribute("paciente", paci);
        model.addAttribute("usuario", usu);
        model.addAttribute("endereco", end);
        model.addAttribute("triagem", tri);
        return "/recepcionista/tela_cadClientes";

    }

    @RequestMapping("/telaPaciAgen")
    public String telaPaciAgen(Model model){
        Paciente paci = new Paciente();

        model.addAttribute("paciente", paci);
        return "/recepcionista/tela_paciAgen";
    }

    @RequestMapping("/conPaciAgen")
    public String conPaciAgen(@ModelAttribute("paciente")Paciente paci, Model model){
        Integer idPaci =recepServ.buscaPacienteporCPF(paci.getCpf());
        paci = paciRepo.findById(idPaci).get();
        Consulta cons = new Consulta();
        Paciente paciente = paci;
        cons.setPaciente(paciente);
        Integer idEsp = 0;
        Integer idCid = 0;
        Integer idCli = 0;
        Float valorMin = 0f;
        Float valorMax = 0f;
        String sexFem = "";
        String sexMas = "";
        Usuario usu = new Usuario();
        Endereco end = new Endereco();
        Triagem tri = new Triagem();
        
        model.addAttribute("usuario", usu);
        model.addAttribute("endereco", end);
        model.addAttribute("paciente", paciente);

        return salvaInfoPaciBD(idEsp, idCid, idCli, valorMin, valorMax, sexFem, sexMas,idPaci, paci, usu, end, tri, model);
        
    }

    @RequestMapping("/addAgenda")
    public String salvaInfoPaciBD(@RequestParam(required = false) Integer idEsp,
    @RequestParam(required = false) Integer idCid, @RequestParam(required = false) Integer idCli,
    @RequestParam(required = false) Float valorMin, @RequestParam(required = false) Float valorMax,
    @RequestParam(required = false) String sexFem, @RequestParam(required = false) String sexMas, 
    @RequestParam(required = false) Integer idPaci, @ModelAttribute("paciente") Paciente paci, 
    @ModelAttribute("usuario") Usuario usu, @ModelAttribute("endereco") Endereco end, Triagem tri, 
    Model model){
        //Salva as Informações
        if(paci.getIdPaci()==null){

            return telaPaciAgen(model);
        }else if(paci.getIdPaci()!=null && usu.getIdUsu()!=null){
        recepServ.criaPaci(paci);
        usuServ.criaUsu(usu);
        recepServ.criaEnd(end);
        tri.setPaciente(paci);
        repoTri.save(tri);
        }
        //Cria a listagem do filtro e seus atributos
        System.out.println("Paciente:"+ paci.getIdPaci());
        paci = paciRepo.findById(idPaci).get();
        List<Cidade> listaCid = recepServ.listarTodosCid();
        List<Especialidade> listaEsp = espServ.findAll();
        List<Medico> listaFiltraMed = medServ.filtraMedCli(idEsp, idCid, idCli, sexMas, sexFem, valorMin, valorMax);
        model.addAttribute("listaCid", listaCid);
        model.addAttribute("listaEsp", listaEsp);
        model.addAttribute("listaFiltraMed", listaFiltraMed);
        return "/recepcionista/tela_agendamento";
    }

    //Seleciona o Medico para saber suas informações
    @RequestMapping("/selecMed")
    public String infoEspecialista(@RequestParam Integer idMed, @RequestParam(required = false) Integer idPaci,
    @ModelAttribute("paciente")Paciente paci, Model model){
        Consulta cons = new Consulta();
        Medico med = medRepo.findById(idMed).get();
        paci = paciRepo.findById(idPaci).get();
        cons.setMedico(med);
        model.addAttribute("medResumo", medServ.medicoResumo(idMed));
        model.addAttribute("doeResumo", doeServ.buscaDoencaPorMedico(idMed));
        model.addAttribute("feedPos", feedServ.buscaPositiva(idMed));
        model.addAttribute("feedNot", feedServ.buscaNegativa(idMed));
        model.addAttribute("total", medServ.buscaQteAtendimento(idMed));
        model.addAttribute("agenda", cons);
        
        return "/recepcionista/tela_agendamento";
    }

    //Salva Informaçoes Adicionais, Horario e Data
    @RequestMapping("/salvaInfoAdic")
    public String salvaInfoAdici(@RequestParam Integer id, @RequestParam(required = false) Integer idPaci,
    @ModelAttribute("agenda")Consulta consulta, @ModelAttribute("paciente")Paciente paci, Model model){
        //Verificar melhor esse metodo
        Date dataAtual = new Date();
        consulta.setDtAgendada(dataAtual.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        Medico med = medRepo.findById(id).get();
        paci = paciRepo.findById(idPaci).get();
        consulta.setMedico(med);
        consulta.setPaciente(paci);
        consServ.cadastro(consulta);
        return "/recepcionista/tela_agendamento";
    }

    //Passa para a tela de Consulta da Recepcionista
    @RequestMapping("/telaAlteraDados")
    public String telaAlteraDados(Model model){
        Paciente paci = new Paciente();

        model.addAttribute("paci", paci);
        return "/recepcionista/tela_paciAltera";
    }

    @RequestMapping("/conPaciAltera")
    public String conPaciAltera(@ModelAttribute("paci") Paciente paci, Model model){
        Integer idPaci =recepServ.buscaPacienteporCPF(paci.getCpf());
        paci = paciRepo.findById(idPaci).get();
        Paciente paciente = paci;
        Usuario usu = usuRepo.findById(paci.getUsuario().getIdUsu()).get();
        Endereco end = endRepo.findById(paci.getEndereco().getIdEnd()).get();

        model.addAttribute("paci", paciente);
        model.addAttribute("usu", usu);
        model.addAttribute("end", end);
        return "/recepcionista/tela_consRes";
    }

    @RequestMapping("/formConsRecep")
    public String formConsultaRecep(@ModelAttribute("paci") Paciente paci,@ModelAttribute("end") Endereco end,
    	    @ModelAttribute("usu") Usuario usu,Model model){
        Integer idPaci = recepServ.buscaPacienteporCPF(paci.getCpf());
        List<Consulta> listaConsulta = consServ.listaCons(idPaci);
        model.addAttribute("listaConsulta", listaConsulta);

        return "/recepcionista/tela_consRes";
    }

    @RequestMapping("/alteraDados")
    public String alteraDadosPaci(@RequestParam(required = false) Integer id ,@ModelAttribute("paci") Paciente paci,@ModelAttribute("end") Endereco end,
    @ModelAttribute("usu") Usuario usu, Model model){
        Paciente paciente = paci;
        String nome = paciente.getNomePaci();
        String cpf = paciente.getCpf();
        LocalDate dataNasc = paciente.getDataNasc();
        String sexo = paciente.getSexo();
        recepServ.atualizaPaci(nome,cpf,dataNasc,sexo,id);
        paci = paciRepo.findById(id).get();
        end.setIdEnd(paci.getEndereco().getIdEnd());
        usu.setIdUsu(paci.getUsuario().getIdUsu());
        recepServ.atualizaEnd(end);
        usuServ.alteraDadosUsuario(usu);
        return painelRecep(model);
    }

    //Passa para a tela de Confirmação e Salva Informaçoes Adicionais, Horario e Data
    @RequestMapping("/posConfirma")
    public String posConfirma(@RequestParam Integer idConsulta,@RequestParam Integer id, 
    @ModelAttribute("agenda")Agenda cons,Model model){
        List<Convenio> listaConv = recepServ.listaConvPorMed(id); 
        List<Consulta> listaResumo = consServ.listaPosConfirma(idConsulta);
        model.addAttribute("listaResumo", listaResumo);
        model.addAttribute("listaConv", listaConv);

        return "/recepcionista/tela_proconfirmation";
    }
    
    //Valida o Agendamento e direciona para a tela da recepionista
    @RequestMapping("/validaAgen")
    public String validaAgen(Model model){
        return painelRecep(model);
    }
   

    //Passa para a tela de Atualizar Recepcionista
    @RequestMapping("/telaAtualizaRecep")
    public String telaAtualizaRecep(Model model){

        Recepcionista recep = new Recepcionista();
        Usuario usu = new Usuario();
        

        model.addAttribute("recep", recep);
        model.addAttribute("usu", usu);

        return "/recepcionista/tela_configuracoes";
    }

    @RequestMapping("/atualizaRecep")
    public String telaAtualizaRecep(@ModelAttribute("recep") Recepcionista recep, 
    @ModelAttribute("usu") Usuario usu, Model model){
        usu.setIdUsu(19);
        recep.setIdRec(1);
        
        usuServ.atualizaUsuario(usu);
        recepServ.atualizaRecep(recep);

        return painelRecep(model);
    }

    @RequestMapping("/telaAjuda")
    public String telaAjuda(Model model){
        model.addAttribute("aju", new Ajuda());
        return "/recepcionista/tela_ajuda";
    }

    @RequestMapping("/telaFormAjuda")
    public String telaFormAjuda(@ModelAttribute("aju")Ajuda aju, Model model){
       Usuario usu = new Usuario();
    	usu.setIdUsu(3);
    	aju.setUsuario(usu);
       ajuServ.criaAjuda(aju);
        return painelRecep(model);
    }

    @RequestMapping("/telaRegPag")
    public String telaRegisPag(Model model){
        Pagamento pag = new Pagamento();
        Consulta cons = new Consulta();
        model.addAttribute("cons", cons);
        model.addAttribute("pag", pag);

        return "/recepcionista/tela_regPayment";
    }

    @RequestMapping("/telaFormPag")
    public String telaFormaPag(@ModelAttribute("cons")Consulta cons, 
    	@ModelAttribute("pag")Pagamento pag, Model model){
        Date dataAtual = new Date();
        DateFormat dataFormatada = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String dataPag = dataFormatada.format(dataAtual);
        pag.setDataPag(dataPag);
        cons.setPagamento(pag);
        pagServ.criaPag(pag);
        consServ.atualizaIdPagNaIdCons(pag.getIdPag(), cons.getIdConsulta());
        return painelRecep(model);
    }

    @RequestMapping("/{idCons}/telaAtualizaAgen")
    public String telaAtualizaAgen(@PathVariable Integer idCons, Model model){
        Optional<Consulta> consOp = this.repoCons.findById(idCons);
        if(consOp.isPresent()){
            Consulta cons = consOp.get();

            model.addAttribute("agen", cons);
        }

        return "/recepcionista/tela_updateSchedule";
    }

    @RequestMapping("/{idCons}/registraPresenca")
    public String registraPresenca(@PathVariable Integer idCons, @RequestParam(required = false) Integer idPaci,
    		@ModelAttribute("paci")Paciente paci,@ModelAttribute("end") Endereco end,
    	    @ModelAttribute("usu") Usuario usu, Model model){
        Optional<Consulta> consOp = this.repoCons.findById(idCons);
        if(consOp.isPresent()){
            consServ.mudaConsulta(idCons);
        }

        return "/recepcionista/tela_consRes";
    }

    @RequestMapping("/telaFormAgen")
    public String telaFormAgen(@ModelAttribute("agen") Consulta cons, Model model){
        consServ.atualizaCons(cons);
        return painelRecep(model);
    }

    @RequestMapping("/telaCancelaAgen")
    public String telaCancelaAgen(Model model){
        Consulta cons = new Consulta();
        model.addAttribute("agen", cons);

        return "/recepcionista/tela_cancelSchedule";
    }

    @RequestMapping("/telaCancelaFormAgen")
    public String telaCancelaFormAgen(@ModelAttribute("agen") Consulta cons, Model model){
        String cpf = cons.getPaciente().getCpf();
        Integer idCons = cons.getIdConsulta();
        List<Consulta> listaCancela = consServ.listaCancelaCons(cpf, idCons);

        model.addAttribute("listaCancela", listaCancela);

        return "/recepcionista/tela_cancelSchedule";
    }

    @PostMapping("/telaCancelaLista")
    public String telaCancelaLista(@RequestParam String cpf, @RequestParam Integer idConsulta,Model model){
        consServ.cancelaConsulta(cpf, idConsulta);
        return painelRecep(model);
    }

    @RequestMapping("/telaSair")
    public String telaSair(){
        
        return "/recepcionista/tela_sair";
    }
}

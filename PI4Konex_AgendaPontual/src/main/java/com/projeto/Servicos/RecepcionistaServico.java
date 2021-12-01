package com.projeto.Servicos;

import java.time.LocalDate;
import java.util.List;

import com.projeto.Entidades.Cidade;
import com.projeto.Entidades.Clinica;
import com.projeto.Entidades.Convenio;
import com.projeto.Entidades.Endereco;
import com.projeto.Entidades.Medico;
import com.projeto.Entidades.Paciente;
import com.projeto.Entidades.Recepcionista;
import com.projeto.Repositorios.CidadeRepositorio;
import com.projeto.Repositorios.ClinicaRepositorio;
import com.projeto.Repositorios.ConvenioRepositorio;
import com.projeto.Repositorios.EnderecoRepositorio;
import com.projeto.Repositorios.MedicoRepositorio;
import com.projeto.Repositorios.PacienteRepositorio;
import com.projeto.Repositorios.RecepcionistaRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecepcionistaServico {

    @Autowired
    private RecepcionistaRepositorio repoRecep;
    @Autowired
    private PacienteRepositorio repoPaci;
    @Autowired
    private EnderecoRepositorio repoEnd;
    @Autowired
    private ClinicaRepositorio repoCli;
    @Autowired
    private CidadeRepositorio repoCid;
    @Autowired
    private MedicoRepositorio repoMed;
    @Autowired
    private ConvenioRepositorio repoConv;

    //Metodos do Crud
    @Transactional(readOnly=false)
    public void criaRecep(Recepcionista recep){
    	 repoRecep.save(recep);

    }

    @Transactional(readOnly=true)
    public Recepcionista pegaRecepPorId(Integer id){
       return repoRecep.getById(id);
    }

    @Transactional(readOnly = false)
    public Recepcionista atualizaRecep(Recepcionista recep){
        Integer idRec = recep.getIdRec();
        Recepcionista recepcionista = repoRecep.findById(idRec).get();
        recepcionista.setNomeRec(recep.getNomeRec());
        recepcionista.setCpfRec(recep.getCpfRec());
        recepcionista.setDataNasc(recep.getDataNasc());
        return repoRecep.save(recepcionista);
    }


    //Metodos do CRUD Paciente
    @Transactional(readOnly=true)
    public List<Paciente> listarTodosPaci(){
        return repoPaci.findAll();
    }
    @Transactional(readOnly=false)
    public void criaPaci(Paciente paci){
        repoPaci.save(paci);
    }
    @Transactional(readOnly=true)
    public Paciente pegaPaciPorId(Integer id){
       return repoPaci.getById(id);
    }
    @Transactional(readOnly=true)
    public void deletaPaciPorId(Integer id){
        repoPaci.deleteById(id);
    }
    @Transactional(readOnly = true)
    public Integer buscaPacienteporCPF(String nome){
        return repoPaci.buscaPacienteporCPF(nome);
    }

    @Transactional(readOnly = false)
    public void atualizaPaci(String nome, String cpf, LocalDate dataNasc, String sexo, Integer id){
        repoPaci.atualizaPaciente(nome, cpf, dataNasc, sexo, id);
    }

    //Metodos do CRUD Endereco
    @Transactional(readOnly = true)
    public List<Endereco> listarTodosEnd(){
        return repoEnd.findAll();
    }

    public void criaEnd(Endereco end){
        repoEnd.save(end);
    }

    public Endereco pegaEndPorId(Integer id){
       return repoEnd.getById(id);
    }

    public void deletaEndPorId(Integer id){
        repoEnd.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Endereco atualizaEnd(Endereco end){
        Integer idEnd = end.getIdEnd();
        Endereco endereco = repoEnd.findById(idEnd).get();
        endereco.setLogradouro(end.getLogradouro());
        endereco.setComplemento(end.getComplemento());
        endereco.setCep(end.getCep());
        endereco.setNumero(end.getNumero());

        return repoEnd.save(endereco);
    }


    //Metodos CRUD Clinica 
    public Clinica pegaCliPorId(Integer id){
        return repoCli.getById(id);
     }

     public List<Clinica> listarTodosCli(){
        return repoCli.findAll();
    }
     
     //Metodos CRUD Cidade
     public List<Cidade> listarTodosCid(){
         return repoCid.findAll();
     }

     //Metodos CRUD Medico
     //Rever melhor Metodos com o ID
     public List<Medico> listarMedCli(Integer idCli){
         return repoMed.listaMedPorCli(idCli);
     }
     public List<Medico> listarTodosMed(){
        return repoMed.findAll();
    }

    //Metodos Convenio
    public List<Convenio> listarTodasConv(){
        return repoConv.findAll();
    }

    public List<Convenio> listaConvPorMed(Integer idMed){
        return repoConv.convPorMed(idMed);
    }

    
}

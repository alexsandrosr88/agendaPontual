package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Dto.ConsultasPacienteDTO;
import com.projeto.Entidades.Consulta;
import com.projeto.Repositorios.AgendaRepositorio;
import com.projeto.Repositorios.ConsultaRepositorio;
import com.projeto.Repositorios.ConsultaRepositorio2;

@Service
public class ConsultaServico {
	
	@Autowired
	private ConsultaRepositorio conRepo;
	
	@Autowired
	private AgendaRepositorio agenRepo;
	
	@Autowired
	private ConsultaRepositorio2 conRepo2;
	
	public void cadastro(Consulta obj) {		
		conRepo.save(obj);
	}
	
	@Transactional(readOnly = true)
	public Consulta buscaConsultaPorId(Integer id) {
		return conRepo.findByIdConsulta(id);
	}

	@Transactional(readOnly = false)
    public Consulta atualizaCons(Consulta cons){
        Integer idCons = cons.getIdConsulta();
        Consulta consulta = conRepo.findById(idCons).get();
        consulta.setDtAgendada(cons.getDtAgendada());
        consulta.setHora(cons.getHora());
        consulta.setInformaAdicio(cons.getInformaAdicio());
        return conRepo.save(consulta);
    }

    @Transactional(readOnly = false)
    public void cancelaConsulta(String cpf, Integer idCons){
        conRepo.deleteById(idCons);
        agenRepo.deleteById(idCons);
    }

	@Transactional(readOnly = true)
    public List<Consulta> listaCons(Integer idPaci) {
        return conRepo.consultaAgendamentos(idPaci);
    }

    @Transactional(readOnly = true)
    public List<Consulta> listagemPainel(Integer idCli) {
        return conRepo.listagemPainel(idCli);
    }

    @Transactional(readOnly = true)
    public List<Consulta> listaCancelaCons(String cpf, Integer idAgen){
        return conRepo.listaCancela(cpf, idAgen);
    }

	@Transactional(readOnly = true)
    public List<Consulta> listaPosConfirma(Integer idAgen){
        return conRepo.listaResumo(idAgen);
    }
	
	@Transactional(readOnly = true)
	public List<Consulta> pesquisaConsultaSemFeed(Integer paciente){
		return conRepo.pesquisaConsultaPorPaciente(paciente);
	}
	
	@Transactional(readOnly = true)
	public List<Consulta> consultasMarcadas(Integer paciente){
		return conRepo.consultasMarcadas(paciente);
	}
	
	@Transactional(readOnly = false)
	public void excluiConsulta(Integer idCons){
		conRepo.deleteById(idCons);
	}
	
	@Transactional(readOnly = false)
    public void mudaConsulta(Integer idCons){
        conRepo.mudaConsulta(idCons);
    }

    @Transactional(readOnly = false)
    public void atualizaIdPagNaIdCons(Integer idPag, Integer idCons){
        conRepo.atualizaIdPagNaIdCons(idPag, idCons);
    }
    
    @Transactional(readOnly = false)
	public void adcionaInformacoes(Integer id, String inforAd) {
    	Consulta consulta = conRepo.findByIdConsulta(id);
    	consulta.setInformaAdicio(inforAd);
	}
    
    @Transactional(readOnly = false)
    public void alteraConsulta(Consulta altera) {
    	Consulta consulta = conRepo.findByIdConsulta(altera.getIdConsulta());
    	 consulta.setDtAgendada(altera.getDtAgendada());
    	 consulta.setHora(altera.getHora());
    	 conRepo.save(consulta);
    }
    
    @Transactional(readOnly = true)
    public List<ConsultasPacienteDTO> consultaPorPaciente(Integer paciente) {
    	return conRepo2.buscaConsultaPorPaciente(paciente);
    } 
    
    @Transactional(readOnly = true)
    public ConsultasPacienteDTO consultaPorIdCons(Integer idCons) {
    	return conRepo2.buscaConsultaPorId(idCons);
    } 
}

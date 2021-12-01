package com.projeto.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Consulta;
import com.projeto.Entidades.Paciente;
import com.projeto.Repositorios.ConsultaRepositorio;
import com.projeto.Repositorios.PacienteRepositorio;

@Service
public class PacienteServico {
	
	@Autowired
	private PacienteRepositorio paciRepo;
	
	@Autowired
	private ConsultaRepositorio consRepo;
	
	@Transactional(readOnly = false)
	public Paciente cadastro(Paciente paciente) {
		return paciRepo.save(paciente);
	}
	
	@Transactional(readOnly = false)
	public void cadastroVoid(Paciente paciente) {
		paciRepo.save(paciente);
	}
	
	@Transactional(readOnly = true)
	public Paciente pesquisaPacientePorUsuarioId(Integer idusu) {
		return paciRepo.buscaPacientePorUsuarioId(idusu);
	}
	
	@Transactional(readOnly = false)
		public void atualizaPacienteConsulta(Integer idCons, Integer idUsu, Paciente paciente ) {
			Paciente doLogin = paciRepo.buscaPacientePorUsuarioId(idUsu);
			doLogin.setPrimeiraConsulta(paciente.getPrimeiraConsulta());
			doLogin.setSintomas(paciente.getSintomas());
			paciRepo.save(doLogin);
			Consulta consulta = consRepo.findByIdConsulta(idCons);
			consulta.setPaciente(doLogin);
			consRepo.save(consulta);
	}
}

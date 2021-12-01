package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Clinica;
import com.projeto.Repositorios.ClinicaRepositorio;

@Service
public class ClinicasServico {
	
	@Autowired
	private ClinicaRepositorio cliRepo;
	
	@Transactional(readOnly = true)
	public List<Object []> buscaClinicas(Integer id){
		return cliRepo.buscaClinicas(id);	
	}
	
	@Transactional(readOnly = false)
	public Clinica atualizaClinica(Clinica clinica){
		return cliRepo.save(clinica);	
	}
	
	@Transactional(readOnly = true)
	public Clinica pesquisaClinicaPorId(Integer idcli) {
		return cliRepo.buscaClinicaPorId(idcli);
	}
}

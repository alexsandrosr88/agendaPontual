package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Convenio;
import com.projeto.Repositorios.ConvenioRepositorio;

@Service
public class ConvenioServico {
	
	@Autowired
	private ConvenioRepositorio conRepo;
	
	@Transactional(readOnly = true)
	public List<Convenio> listaConvenio(){
		return conRepo.findAll();
	}

}
